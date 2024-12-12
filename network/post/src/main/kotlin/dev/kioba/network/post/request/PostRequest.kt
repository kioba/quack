package dev.kioba.network.post.request

import arrow.core.Either
import dev.kioba.network.post.model.PostResponse
import dev.kioba.platform.network.NetworkScope
import dev.kioba.platform.network.createApi
import retrofit2.http.GET

internal interface PostApi {

  @GET("/posts")
  suspend fun getPosts(): Either<String, List<PostResponse>>
}


internal inline fun <R> NetworkScope.postRequest(
  block: PostApi.() -> R,
): R =
  createApi<PostApi>().block()
