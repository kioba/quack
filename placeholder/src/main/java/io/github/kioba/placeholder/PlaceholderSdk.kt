package io.github.kioba.placeholder

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.kioba.placeholder.network.JsonPlaceholderApi
import io.github.kioba.placeholder.network.network_models.Comment
import io.github.kioba.placeholder.post.DatabasePost
import io.github.kioba.placeholder.post.IPostModule
import io.github.kioba.placeholder.post.NetworkPost
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.post.toDatabase
import io.github.kioba.placeholder.post.toModel
import io.github.kioba.placeholder.user.DatabaseUser
import io.github.kioba.placeholder.user.IUserModule
import io.github.kioba.placeholder.user.NetworkUser
import io.github.kioba.placeholder.user.User
import io.github.kioba.placeholder.user.toDatabase
import io.github.kioba.placeholder.user.toModel
import io.reactivex.Flowable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PlaceholderSdk @Inject constructor(
  private val userDatabase: IUserModule,
  private val postDatabase: IPostModule
) : IPlaceholderSdk {

  private val logInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

  private val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(logInterceptor)
    .build()

  private val retrofit: Retrofit = Retrofit
    .Builder()
    .baseUrl(api)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

  private val jsonPlaceholderApi: JsonPlaceholderApi =
    retrofit.create(JsonPlaceholderApi::class.java)

  override fun getUsers(): Flowable<List<User>> =
    userDatabase.usersStream().map { it.map(DatabaseUser::toModel) }
      .publish { share ->
        Flowable.concat(
          share.take(1),
          syncUsers().take(1),
          share.skip(1)
        )
      }.distinctUntilChanged()


  override fun getUser(userId: Int): Flowable<User> =
    Flowable.concat(
      userDatabase.getUser(userId).map(DatabaseUser::toModel).toFlowable(),
      syncUser(userId).take(1),
      userDatabase.userStream(userId).map(DatabaseUser::toModel)
    ).distinctUntilChanged()


  override fun getFeed(): Flowable<List<Post>> =
    postDatabase.postsStream().map { it.map(DatabasePost::toModel) }
      .publish { share ->
        Flowable.concat(
          share.take(1),
          syncFeed().take(1),
          share.skip(1)
        )
      }.distinctUntilChanged()

  override fun getPost(postId: Int): Flowable<Post> =
    Flowable.concat(
      postDatabase.getPost(postId)
        .map(DatabasePost::toModel).toFlowable(),
      syncPost(postId).take(1),
      postDatabase.postStream(postId)
        .map(DatabasePost::toModel)
    )

  override fun getComments(postId: Int): Flowable<List<Comment>> =
    jsonPlaceholderApi
      .getComments(postId)
      .toFlowable()

  companion object {
    private const val api: String = "https://jsonplaceholder.typicode.com"
  }


  private fun syncUsers(): Flowable<List<User>> = jsonPlaceholderApi.getUsers()
    .map { it.map(NetworkUser::toDatabase) }
    .flatMap { databaseUser ->
      userDatabase.insertUsers(databaseUser)
        .toSingle { databaseUser.map(DatabaseUser::toModel) }
        .onErrorReturn { databaseUser.map(DatabaseUser::toModel) }
    }
    .toFlowable()

  private fun syncUser(userId: Int): Flowable<User> = jsonPlaceholderApi
    .getUser(userId)
    .map(NetworkUser::toDatabase)
    .flatMap { databaseUser ->
      userDatabase.insertUser(databaseUser)
        .toSingle { databaseUser.toModel() }
        .onErrorReturn { databaseUser.toModel() }
    }
    .toFlowable()

  private fun syncFeed() =
    jsonPlaceholderApi.getFeed()
      .map { it.map(NetworkPost::toDatabase) }
      .flatMap { databasePosts ->
        postDatabase.insertPosts(databasePosts)
          .toSingle { databasePosts.map(DatabasePost::toModel) }
          .onErrorReturn { databasePosts.map(DatabasePost::toModel) }
      }
      .toFlowable()

  private fun syncPost(postId: Int) =
    jsonPlaceholderApi
      .getPost(postId)
      .map(NetworkPost::toDatabase)
      .flatMap { databasePost ->
        postDatabase.insertPost(databasePost)
          .toSingle { databasePost.toModel() }
          .onErrorReturn { databasePost.toModel() }
      }
      .toFlowable()

}
