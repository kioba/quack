package io.github.kioba.platform.domain

import arrow.core.Either
import arrow.core.recover
import io.github.kioba.platform.database.DatabaseScope
import io.github.kioba.platform.network.NetworkConverter
import io.github.kioba.platform.network.NetworkScope
import kotlinx.coroutines.flow.Flow

public fun buildUseCaseManager(
  databaseScope: DatabaseScope,
): UseCaseManager =
  UseCaseManager(
    useCaseScope = UseCaseScope(NetworkManager(), DatabaseManager(databaseScope)),
  )

public class UseCaseManager internal constructor(
  @PublishedApi
  internal val useCaseScope: UseCaseScope,
)

@DomainDsl
public class UseCaseScope(
  @PublishedApi
  internal val networkManager: NetworkManager,
  @PublishedApi
  internal val databaseManager: DatabaseManager,
)

context (UseCaseScope)
@DomainDsl
public inline fun <E, R> syncFirstStrategy(
  sync: @DomainDsl NetworkScope.() -> Either<E, R>,
  insert: @DomainDsl DatabaseScope.(R) -> Unit,
  read: @DomainDsl DatabaseScope.() -> Either<E, R>,
): Either<E, R> =
  sync(networkManager.networkScope)
    .onRight { insert(databaseManager.databaseScope, it) }
    .recover { read(databaseManager.databaseScope).bind() }

@DomainDsl
public inline fun <R> UseCaseScope.cacheOnlyStrategy(
  read: @DomainDsl DatabaseScope.() -> R,
): R =
  read(databaseManager.databaseScope)

@DomainDsl
public inline fun <R> UseCaseScope.cacheOnlyFlowableStrategy(
  read: @DomainDsl DatabaseScope.() -> Flow<R>,
): Flow<R> =
  read(databaseManager.databaseScope)

context (NetworkConverter<R, N>)
@DomainDsl
public inline fun <R, N> UseCaseScope.networkOnlyStrategy(
  sync: @DomainDsl NetworkScope.() -> Either<Throwable, N>,
): Either<Throwable, R> =
  sync(networkManager.networkScope)
    .map { it.fromNetworkToDomain() }
