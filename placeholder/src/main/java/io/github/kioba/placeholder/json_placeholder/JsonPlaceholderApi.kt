package io.github.kioba.placeholder.json_placeholder

import io.github.kioba.placeholder.json_placeholder.network_models.Comment
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.github.kioba.placeholder.json_placeholder.network_models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderApi {

  @GET("/posts")
  fun getFeed(): Single<List<Post>>

  @GET("/posts/{postId}")
  fun getPost(@Path("postId") postId: Int): Single<Post>

  @GET("/users")
  fun getUsers(): Single<List<User>>

  @GET("/users/{userId}")
  fun getUser(@Path("userId") userId: Int): Single<User>

  @GET("/posts/{postId}/comments")
  fun getComments(@Path("postId") postId: Int): Single<List<Comment>>
}
