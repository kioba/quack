package io.github.kioba.placeholder

import arrow.core.Either
import arrow.core.orNull
import arrow.core.right
import io.github.kioba.persistence.user.insertUser
import io.github.kioba.persistence.user.insertUsers
import io.github.kioba.persistence.user.readUsers
import io.github.kioba.placeholder.converter.CommentListConverter
import io.github.kioba.placeholder.converter.PostListConverter
import io.github.kioba.placeholder.converter.UserConverter
import io.github.kioba.placeholder.converter.UserListConverter
import io.github.kioba.placeholder.network.JsonPlaceholderApi
import io.github.kioba.placeholder.network.model.Comment
import io.github.kioba.placeholder.post.DatabasePost
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.post.PostModule
import io.github.kioba.placeholder.post.toModel
import io.github.kioba.placeholder.user.DatabaseUser
import io.github.kioba.placeholder.user.User
import io.github.kioba.placeholder.user.UserModule
import io.github.kioba.placeholder.user.toModel
import io.github.kioba.platform.domain.DomainScope
import io.github.kioba.platform.domain.fetchFlowable
import io.github.kioba.platform.domain.storage
import io.github.kioba.platform.domain.syncFirstStrategy
import io.github.kioba.platform.domain.useCase
import io.github.kioba.platform.domain.useCaseFlowable
import io.github.kioba.platform.network.NetworkScope
import io.github.kioba.platform.network.createApi
import io.reactivex.Flowable
import javax.inject.Inject

context(NetworkScope)
  private inline fun <R> placeholderApi(
  block: JsonPlaceholderApi.() -> R,
): R =
  createApi<JsonPlaceholderApi>().block()

context (DomainScope)
  suspend fun syncUsers(): Either<Throwable, List<User>> =
  useCase {
    with(UserListConverter) {
      syncFirstStrategy(
        sync = { placeholderApi { syncUsersAsync() } },
        insert = { entities -> insertUsers(entities) },
        read = { readUsers().right() },
      )
    }
  }

context (DomainScope)
fun readUser(): Either<Throwable, List<User>> =
  useCase {
    with(UserListConverter) {
      storage { readUsers().right() }
    }
  }

class PlaceholderSdk @Inject constructor(
  private val userDatabase: UserModule,
  private val postDatabase: PostModule,
) {
  context (DomainScope)
  fun getUsers(): Flowable<List<User>> =
    userDatabase.usersStream().map { it.map(DatabaseUser::toModel) }
      .publish { share ->
        Flowable.concat(
          share.take(1),
          syncUsers().take(1),
          share.skip(1)
        )
      }.distinctUntilChanged()


  context (DomainScope)
  fun getUser(userId: Long): Flowable<User> =
    Flowable.concat(
      userDatabase.getUser(userId).map(DatabaseUser::toModel).toFlowable(),
      syncUser(userId).take(1),
      userDatabase.userStream(userId).map(DatabaseUser::toModel)
    ).distinctUntilChanged()


  context (DomainScope)
  fun getFeed(): Flowable<List<Post>> =
    postDatabase.postsStream().map { it.map(DatabasePost::toModel) }
      .publish { share ->
        Flowable.concat(
          share.take(1),
          syncFeed().take(1),
          share.skip(1)
        )
      }.distinctUntilChanged()

  context(DomainScope)
  fun getComments(postId: Int): Flowable<List<Comment>> =
    useCaseFlowable {
      with(CommentListConverter) {
        fetchFlowable {
          placeholderApi { getComments(postId) }
            .toFlowable()
        }
      }
    }
      .map { it.fold({ emptyList() }, { posts -> posts }) }

  context(DomainScope)
    private fun syncUsers(): Flowable<List<User>> =
    useCaseFlowable {
      with(UserListConverter) {
        fetchFlowable {
          placeholderApi { getUsers() }
            .toFlowable()
        }
      }
        .doOnNext { databaseUser ->
          with(UserListConverter) {
            storage {
              databaseUser.fromDomainToEntity()
                .also { insertUsers(it) }
                .right()
            }
          }
        }
    }.map { it.fold({ emptyList() }, { posts -> posts }) }

  context(DomainScope)
    private fun syncUser(userId: Long): Flowable<User> =
    useCaseFlowable {
      with(UserConverter) {
        fetchFlowable {
          placeholderApi { getUser(userId) }
            .toFlowable()
        }
      }
        .doOnNext { databaseUser ->
          with(UserConverter) {
            storage {
              databaseUser.fromDomainToEntity()
                .also { insertUser(it) }
                .right()
            }
          }
        }
    }.map { it.orNull()!! }

  context(DomainScope)
    private fun syncFeed(): Flowable<List<Post>> =
    useCaseFlowable {
      with(PostListConverter) {
        fetchFlowable {
          placeholderApi { getFeed() }
            .toFlowable()
        }
      }
    }.map { it.fold({ emptyList() }, { posts -> posts }) }

}
