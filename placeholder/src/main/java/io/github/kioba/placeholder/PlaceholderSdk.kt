package io.github.kioba.placeholder

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.github.kioba.placeholder.network.JsonPlaceholderApi
import io.github.kioba.placeholder.network.network_models.Comment
import io.github.kioba.placeholder.network.network_models.Post
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
  private val userDatabase: IUserModule
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

  override fun getFeed(): Flowable<List<Post>> = jsonPlaceholderApi
    .getFeed()
    .toFlowable()

  private fun syncUsers(): Flowable<List<User>> = jsonPlaceholderApi.getUsers()
    .map { it.map(NetworkUser::toDatabase) }
    .flatMapCompletable(userDatabase::insertUsers)
    .onErrorComplete()
    .toFlowable()

  override fun getUsers(): Flowable<List<User>> =
    userDatabase.usersStream().map { it.map(DatabaseUser::toModel) }
      .publish { share ->
        Flowable.concat(
          share.take(1),
          syncUsers(),
          share.skip(1)
        )
      }.distinctUntilChanged()

  private fun syncUser(userId: Int): Flowable<User> = jsonPlaceholderApi
    .getUser(userId)
    .map(NetworkUser::toDatabase)
    .flatMapCompletable(userDatabase::insertUser)
    .onErrorComplete()
    .toFlowable()

  override fun getUser(userId: Int): Flowable<User> =
    Flowable.concat(
      userDatabase.getUser(userId).map(DatabaseUser::toModel).toFlowable(),
      syncUser(userId),
      userDatabase.userStream(userId).map(DatabaseUser::toModel)
    ).distinctUntilChanged()

  override fun getPost(postId: Int): Flowable<Post> =
    jsonPlaceholderApi
      .getPost(postId)
      .toFlowable()

  override fun getComments(postId: Int): Flowable<List<Comment>> =
    jsonPlaceholderApi
      .getComments(postId)
      .toFlowable()

  companion object {
    private const val api: String = "https://jsonplaceholder.typicode.com"
  }

}
