package dev.kioba.network.user

import arrow.core.Either
import dev.kioba.network.user.model.UserResponse
import dev.kioba.network.user.request.userRequest
import dev.kioba.platform.network.NetworkScope

public suspend fun NetworkScope.fetchUsers(): Either<Throwable, List<UserResponse>> =
  userRequest { fetchUsers() }
    .mapLeft { Exception(it) }

public suspend fun NetworkScope.fetchUser(
  userId: Long,
): Either<Throwable, UserResponse> =
  userRequest { fetchUser(userId) }
    .mapLeft { Exception(it) }
