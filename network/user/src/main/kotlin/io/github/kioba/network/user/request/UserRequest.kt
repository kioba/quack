package io.github.kioba.network.user.request

import arrow.core.Either
import io.github.kioba.network.user.model.UserResponse
import io.github.kioba.platform.network.NetworkScope
import io.github.kioba.platform.network.createApi
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PlaceholderUserApi {

  @GET("/users")
  suspend fun fetchUsers(): Either<Throwable, List<UserResponse>>

  @GET("/users/{userId}")
  suspend fun fetchUser(
    @Path("userId") userId: Long,
  ): Either<Throwable, UserResponse>
}


context(NetworkScope)
  internal inline fun <R> userRequest(
  block: PlaceholderUserApi.() -> R,
): R =
  createApi<PlaceholderUserApi>().block()