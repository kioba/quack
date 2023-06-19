package io.github.kioba.network.post.request

import arrow.core.Either
import io.github.kioba.network.post.model.PostResponse
import io.github.kioba.platform.network.NetworkScope
import io.github.kioba.platform.network.createApi
import retrofit2.http.GET

internal interface PostApi {

  @GET("/posts")
  suspend fun getPosts(): Either<String, List<PostResponse>>
}


context(NetworkScope)
  internal inline fun <R> postRequest(
  block: PostApi.() -> R,
): R =
  createApi<PostApi>().block()