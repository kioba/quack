package io.github.kioba.placeholder

import arrow.core.Either
import arrow.core.extensions.either.applicativeError.handleErrorWith
import arrow.core.extensions.either.monad.flatten
import arrow.core.left
import arrow.core.orNull
import arrow.core.right
import io.github.kioba.persistence.user.insertUsers
import io.github.kioba.persistence.user.readUsers
import io.github.kioba.placeholder.converter.UserListConverter
import io.github.kioba.placeholder.network.JsonPlaceholderApi
import io.github.kioba.placeholder.network.model.Comment
import io.github.kioba.placeholder.post.DatabasePost
import io.github.kioba.placeholder.post.NetworkPost
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.post.PostModule
import io.github.kioba.placeholder.post.toDatabase
import io.github.kioba.placeholder.post.toModel
import io.github.kioba.placeholder.user.DatabaseUser
import io.github.kioba.placeholder.user.User
import io.github.kioba.placeholder.user.UserModule
import io.github.kioba.placeholder.user.UserNetwork
import io.github.kioba.placeholder.user.toDatabase
import io.github.kioba.placeholder.user.toModel
import io.github.kioba.platform.database.DatabaseScope
import io.github.kioba.platform.network.NetworkScope
import io.github.kioba.platform.network.createApi
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject


context(NetworkScope)
  private inline fun <R> placeholderApi(
  block: JsonPlaceholderApi.() -> R,
): R =
  createApi<JsonPlaceholderApi>().block()


class PlaceholderSdk @Inject constructor(
  private val userDatabase: UserModule,
  private val postDatabase: PostModule,
) {
  context (EffectScope)
  fun getUsers(): Flowable<List<User>> =
    userDatabase.usersStream().map { it.map(DatabaseUser::toModel) }
      .publish { share ->
        Flowable.concat(
          share.take(1),
          syncUsers().take(1),
          share.skip(1)
        )
      }.distinctUntilChanged()

  context (EffectScope)
    suspend fun getUsersAsync(): Either<Throwable, List<User>> =
    useCase {
      with(UserListConverter) {
        syncFirst(
          sync = { placeholderApi { syncUsersAsync() } },
          insert = { entities -> insertUsers(entities) },
          read = { readUsers().right() },
        )
      }
    }

  context (EffectScope)
  fun getUser(userId: Long): Flowable<User> =
    Flowable.concat(
      userDatabase.getUser(userId).map(DatabaseUser::toModel).toFlowable(),
      syncUser(userId).take(1),
      userDatabase.userStream(userId).map(DatabaseUser::toModel)
    ).distinctUntilChanged()


  context (EffectScope)
  fun getFeed(): Flowable<List<Post>> =
    postDatabase.postsStream().map { it.map(DatabasePost::toModel) }
      .publish { share ->
        Flowable.concat(
          share.take(1),
          syncFeed().take(1),
          share.skip(1)
        )
      }.distinctUntilChanged()

  context(EffectScope)
  fun getComments(postId: Int): Flowable<List<Comment>> =
    useCaseFlowable {
      with(networkManager.networkScope) {
        placeholderApi { getComments(postId) }
          .toFlowable()
      }
    }.map { it.fold({ emptyList() }, { posts -> posts }) }

  context(EffectScope)
    private fun syncUsers(): Flowable<List<User>> =
    useCaseFlowable {
      with(networkManager.networkScope) {
        placeholderApi { getUsers() }
      }
        .map { it.map(UserNetwork::toDatabase) }
        .flatMap { databaseUser ->
          userDatabase.insertUsers(databaseUser)
            .toSingle { databaseUser.map(DatabaseUser::toModel) }
            .onErrorReturn { databaseUser.map(DatabaseUser::toModel) }
        }
        .toFlowable()
    }.map { it.fold({ emptyList() }, { posts -> posts }) }

  context(EffectScope)
    private fun syncUser(userId: Long): Flowable<User> =
    useCaseFlowable {
      with(networkManager.networkScope) {
        placeholderApi { getUser(userId) }
      }
        .map(UserNetwork::toDatabase)
        .flatMap { databaseUser ->
          userDatabase.insertUser(databaseUser)
            .toSingle { databaseUser.toModel() }
            .onErrorReturn { databaseUser.toModel() }
        }
        .toFlowable()
    }.map { it.orNull()!! }

  context(EffectScope)
    private fun syncFeed(): Flowable<List<Post>> =
    useCaseFlowable {
      with(networkManager.networkScope) { placeholderApi { getFeed() } }
        .map { it.map(NetworkPost::toDatabase) }
        .flatMap { databasePosts ->
          with(databaseManager.databaseScope) { insertPosts(databasePosts) }
            .toSingle { databasePosts.map(DatabasePost::toModel) }
            .onErrorReturn { databasePosts.map(DatabasePost::toModel) }
        }
        .toFlowable()
    }.map { it.fold({ emptyList() }, { posts -> posts }) }

  context (DatabaseScope)
    private fun insertPosts(databasePosts: List<DatabasePost>): Completable =
    postDatabase.insertPosts(databasePosts)

  context(EffectScope)
    private fun syncPost(postId: Int) =
    useCaseFlowable {
      with(networkManager.networkScope) {
        placeholderApi { getPost(postId) }
      }
        .map(NetworkPost::toDatabase)
        .flatMap { databasePost ->
          postDatabase.insertPost(databasePost)
            .toSingle { databasePost.toModel() }
            .onErrorReturn { databasePost.toModel() }
        }
        .toFlowable()
    }

}

public fun buildEffects(
  databaseScope: DatabaseScope,
): EffectScope =
  object : EffectScope {
    override val useCaseManager: RepositoryManager = repository(databaseScope)
  }

interface EffectScope {
  val useCaseManager: RepositoryManager
}

//internal inline fun <R> EffectScope.useCase(
//  f: UseCaseScope.() -> R,
//): Either<Throwable, R> =
//  useCaseManager.useCaseScope
//    .runCatching(f)
//    .fold(Either.Companion::right, Either.Companion::left)

internal inline fun <R> EffectScope.useCase(
  f: UseCaseScope.() -> Either<Throwable, R>,
): Either<Throwable, R> =
  useCaseManager.useCaseScope
    .runCatching(f)
    .fold(Either.Companion::right, Either.Companion::left)
    .flatten()

internal inline fun <R> EffectScope.useCaseFlowable(
  f: UseCaseScope.() -> Flowable<R>,
): Flowable<Either<Throwable, R>> =
  useCaseManager.useCaseScope.f()
    .map { it.right() as Either<Throwable, R> }
    .onErrorReturn { it.left() }


public fun repository(
  databaseScope: DatabaseScope,
): RepositoryManager =
  RepositoryManager(
    useCaseScope = UseCaseScope(NetworkManager(), DatabaseManager(databaseScope)),
  )

public class RepositoryManager internal constructor(
  @PublishedApi
  internal val useCaseScope: UseCaseScope,
)

internal class UseCaseScope(
  val networkManager: NetworkManager,
  val databaseManager: DatabaseManager,
)

context (UseCaseScope, NetworkConverter<R, N>, EntityConverter<R, E>)
  internal inline fun <R, N, E> syncFirst(
  sync: context(NetworkScope) () -> Either<Throwable, N>,
  insert: context(DatabaseScope) (E) -> Unit,
  crossinline read: context(DatabaseScope) () -> Either<Throwable, E>,
): Either<Throwable, R> =
  sync(networkManager.networkScope)
    .map { it.fromNetworkToDomain() }
    .map { insert(databaseManager.databaseScope, it.fromDomainToEntity()); it }
    .handleErrorWith { read(databaseManager.databaseScope).map { it.fromEntityToDomain() } }

context (UseCaseScope, NetworkConverter<R, N>, EntityConverter<R, E>)
  internal inline fun <R, N, E> syncListFirst(
  sync: context(NetworkScope) () -> Either<Throwable, List<N>>,
  insert: context(DatabaseScope) (List<E>) -> Unit,
  crossinline read: context(DatabaseScope) () -> Either<Throwable, List<E>>,
): Either<Throwable, List<R>> =
  sync(networkManager.networkScope)
    .map { it.map { it.fromNetworkToDomain() } }
    .map { insert(databaseManager.databaseScope, it.map { it.fromDomainToEntity() }); it }
    .handleErrorWith { read(databaseManager.databaseScope).map { it.map { it.fromEntityToDomain() } } }

interface NetworkConverter<R, N> {
  fun N.fromNetworkToDomain(): R
}

interface EntityConverter<R, P> {
  fun P.fromEntityToDomain(): R
  fun R.fromDomainToEntity(): P
}
