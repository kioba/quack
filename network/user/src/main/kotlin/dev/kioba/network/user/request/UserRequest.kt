package dev.kioba.network.user.request

import arrow.core.Either
import dev.kioba.network.user.model.UserResponse
import dev.kioba.platform.network.NetworkScope
import dev.kioba.platform.network.createApi
import retrofit2.http.GET
import retrofit2.http.Path

internal interface PlaceholderUserApi {

  @GET("/users")
  suspend fun fetchUsers(): Either<String, List<UserResponse>>

  @GET("/users/{userId}")
  suspend fun fetchUser(
    @Path("userId") userId: Long,
  ): Either<String, UserResponse>
}

internal inline fun <R> NetworkScope.userRequest(
  block: PlaceholderUserApi.() -> R,
): R =
  createApi<PlaceholderUserApi>()
    .block()
