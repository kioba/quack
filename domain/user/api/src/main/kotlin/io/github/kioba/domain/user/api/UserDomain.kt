package io.github.kioba.domain.user.api

import arrow.core.Either
import arrow.core.right
import io.github.kioba.domain.placeholder.user.model.User
import io.github.kioba.domain.placeholder.user.model.UserId
import io.github.kioba.domain.user.api.converter.toDomain
import io.github.kioba.domain.user.api.converter.toEntity
import io.github.kioba.network.user.fetchUser
import io.github.kioba.network.user.fetchUsers
import io.github.kioba.persistence.user.insertUser
import io.github.kioba.persistence.user.insertUsers
import io.github.kioba.persistence.user.readUser
import io.github.kioba.persistence.user.readUsers
import io.github.kioba.persistence.user.streamUsers
import io.github.kioba.platform.domain.DomainScope
import io.github.kioba.platform.domain.cacheOnlyFlowableStrategy
import io.github.kioba.platform.domain.syncFirstStrategy
import io.github.kioba.platform.domain.useCase
import io.github.kioba.platform.domain.useCaseFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public suspend fun DomainScope.syncUsers(): Either<Throwable, List<User>> =
  useCase {
    syncFirstStrategy(
      sync = { fetchUsers().map { it.map { user -> user.toDomain() } } },
      insert = { insertUsers(it.map { user -> user.toEntity() }) },
      read = { readUsers().map { it.toDomain() }.right() },
    )
  }

public suspend fun DomainScope.syncUser(
  userId: UserId,
): Either<Throwable, User?> =
  useCase {
    syncFirstStrategy(
      sync = { fetchUser(userId.value).map { it.toDomain() } },
      insert = { it?.toEntity()?.let(::insertUser) },
      read = { readUser(userId.value)?.toDomain().right() },
    )
  }

public fun DomainScope.listenUsers(): Flow<List<User>> =
  useCaseFlow {
    cacheOnlyFlowableStrategy { streamUsers() }
      .map { it.map { entity -> entity.toDomain() } }
  }
