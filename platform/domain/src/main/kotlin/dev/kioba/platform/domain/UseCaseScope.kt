package dev.kioba.platform.domain

import arrow.core.Either
import arrow.core.recover
import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.network.NetworkConverter
import dev.kioba.platform.network.NetworkScope
import kotlinx.coroutines.flow.Flow

public fun buildUseCaseManager(
  databaseScope: DatabaseScope,
  networkScope: NetworkScope,
): UseCaseManager =
  UseCaseManager(
    useCaseScope = UseCaseScope(NetworkManager(networkScope), DatabaseManager(databaseScope)),
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

@DomainDsl
public inline fun <E, R> UseCaseScope.syncFirstStrategy(
  sync: @DomainDsl NetworkScope.() -> Either<E, R>,
  insert: @DomainDsl DatabaseScope.(R) -> Unit,
  read: @DomainDsl DatabaseScope.() -> Either<E, R>,
): Either<E, R> =
  sync(networkManager.networkScope)
    .onRight { insert(databaseManager.databaseScope, it) }
    .recover { read(databaseManager.databaseScope).bind() }

@DomainDsl
public inline fun <E, R> UseCaseScope.syncOnMissingStrategy(
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

@DomainDsl
public inline fun <R, N> UseCaseScope.networkOnlyStrategy(
  converter: NetworkConverter<R, N>,
  sync: @DomainDsl NetworkScope.() -> Either<Throwable, N>,
): Either<Throwable, R> =
  sync(networkManager.networkScope)
    .map { with(converter) { it.fromNetworkToDomain() } }
