package io.github.kioba.network.user

import arrow.core.Either
import io.github.kioba.network.user.model.UserResponse
import io.github.kioba.network.user.request.userRequest
import io.github.kioba.platform.network.NetworkScope

context(NetworkScope)
  public suspend fun fetchUsers(): Either<Throwable, List<UserResponse>> =
  userRequest { fetchUsers() }
    .mapLeft { Exception(it) }

context(NetworkScope)
  public suspend fun fetchUser(
  userId: Long,
): Either<Throwable, UserResponse> =
  userRequest { fetchUser(userId) }
    .mapLeft { Exception(it) }
