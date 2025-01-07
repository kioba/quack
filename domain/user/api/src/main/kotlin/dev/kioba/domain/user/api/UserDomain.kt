package dev.kioba.domain.user.api

import arrow.core.Either
import arrow.core.right
import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.placeholder.user.model.UserId
import dev.kioba.domain.user.api.converter.toDomain
import dev.kioba.domain.user.api.converter.toEntity
import dev.kioba.network.user.fetchUser
import dev.kioba.network.user.fetchUsers
import dev.kioba.persistence.user.insertUser
import dev.kioba.persistence.user.insertUsers
import dev.kioba.persistence.user.readUser
import dev.kioba.persistence.user.readUsers
import dev.kioba.persistence.user.streamUsers
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.cacheOnlyFlowableStrategy
import dev.kioba.platform.domain.syncFirstStrategy
import dev.kioba.platform.domain.useCase
import dev.kioba.platform.domain.useCaseFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public suspend fun EffectContext.syncUsers(): Either<Throwable, List<User>> =
  useCase {
    syncFirstStrategy(
      sync = { fetchUsers().map { it.map { user -> user.toDomain() } } },
      insert = { insertUsers(it.map { user -> user.toEntity() }) },
      read = { readUsers().map { it.toDomain() }.right() },
    )
  }

public suspend fun EffectContext.syncUser(
  userId: UserId,
): Either<Throwable, User?> =
  useCase {
    syncFirstStrategy(
      sync = { fetchUser(userId.value).map { it.toDomain() } },
      insert = { it?.toEntity()?.let(::insertUser) },
      read = { readUser(userId.value)?.toDomain().right() },
    )
  }

public fun EffectContext.listenUsers(): Flow<List<User>> =
  useCaseFlow {
    cacheOnlyFlowableStrategy { streamUsers() }
      .map { it.map { entity -> entity.toDomain() } }
  }
