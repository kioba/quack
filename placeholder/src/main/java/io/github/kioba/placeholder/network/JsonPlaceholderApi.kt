package io.github.kioba.placeholder.network

import io.github.kioba.placeholder.network.network_models.Comment
import io.github.kioba.placeholder.post.NetworkPost
import io.github.kioba.placeholder.user.NetworkUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderApi {

  @GET("/posts")
  fun getFeed(): Single<List<NetworkPost>>

  @GET("/posts/{postId}")
  fun getPost(@Path("postId") postId: Int): Single<NetworkPost>

  @GET("/users")
  fun getUsers(): Single<List<NetworkUser>>

  @GET("/users/{userId}")
  fun getUser(@Path("userId") userId: Int): Single<NetworkUser>

  @GET("/posts/{postId}/comments")
  fun getComments(@Path("postId") postId: Int): Single<List<Comment>>
}
