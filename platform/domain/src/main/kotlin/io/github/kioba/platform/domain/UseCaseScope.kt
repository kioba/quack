package io.github.kioba.platform.domain

import arrow.core.Either
import arrow.core.handleErrorWith
import io.github.kioba.platform.database.DatabaseScope
import io.github.kioba.platform.database.EntityConverter
import io.github.kioba.platform.network.NetworkConverter
import io.github.kioba.platform.network.NetworkScope
import io.reactivex.Flowable

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

public class UseCaseScope(
  @PublishedApi
  internal val networkManager: NetworkManager,
  @PublishedApi
  internal val databaseManager: DatabaseManager,
)

context (UseCaseScope, NetworkConverter<R, N>, EntityConverter<R, E>)
  public inline fun <R, N, E> syncFirstStrategy(
  sync: context(NetworkScope) () -> Either<Throwable, N>,
  insert: context(DatabaseScope) (E) -> Unit,
  crossinline read: context(DatabaseScope) () -> Either<Throwable, E>,
): Either<Throwable, R> =
  sync(networkManager.networkScope)
    .map { it.fromNetworkToDomain() }
    .map { insert(databaseManager.databaseScope, it.fromDomainToEntity()); it }
    .handleErrorWith { read(databaseManager.databaseScope).map { it.fromEntityToDomain() } }

context (UseCaseScope, EntityConverter<R, E>)
  public inline fun <R, E> storage(
  read: context(DatabaseScope) () -> Either<Throwable, E>,
): Either<Throwable, R> =
  read(databaseManager.databaseScope).map { it.fromEntityToDomain() }

context (UseCaseScope, NetworkConverter<R, N>)
  public inline fun <R, N> fetch(
  sync: context(NetworkScope) () -> Either<Throwable, N>,
): Either<Throwable, R> =
  sync(networkManager.networkScope)
    .map { it.fromNetworkToDomain() }

context (UseCaseScope, NetworkConverter<R, N>)
  public inline fun <R, N> fetchFlowable(
  sync: context(NetworkScope) () -> Flowable<N>,
): Flowable<R> =
  sync(networkManager.networkScope)
    .map { response -> response.fromNetworkToDomain() }

