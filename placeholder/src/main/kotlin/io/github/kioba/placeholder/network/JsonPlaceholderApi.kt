package io.github.kioba.placeholder.network

import arrow.core.Either
import io.github.kioba.network.user.model.UserResponse
import io.github.kioba.placeholder.network.model.Comment
import io.github.kioba.placeholder.post.NetworkPost
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderApi {

  @GET("/posts")
  fun getFeed(): Single<List<NetworkPost>>

  @GET("/posts/{postId}")
  fun getPost(@Path("postId") postId: Int): Single<NetworkPost>

  @GET("/users")
  fun getUsers(): Single<List<UserResponse>>

  @GET("/users")
  suspend fun syncUsersAsync(): Either<Throwable, List<UserResponse>>

  @GET("/users/{userId}")
  fun getUser(@Path("userId") userId: Long): Single<UserResponse>

  @GET("/posts/{postId}/comments")
  fun getComments(@Path("postId") postId: Int): Single<List<Comment>>
}
