package dev.kioba.platform.domain

import arrow.core.Either
import arrow.core.flatten
import dev.kioba.platform.database.DatabaseScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


public fun buildDomain(
  databaseScope: DatabaseScope,
): DomainScope =
  object : DomainScope {
    override val useCaseManager: UseCaseManager = buildUseCaseManager(databaseScope)
  }

public interface DomainScope {
  public val useCaseManager: UseCaseManager
}

@Target(
  AnnotationTarget.CLASS,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.TYPEALIAS,
  AnnotationTarget.TYPE
)
@DslMarker
public annotation class DomainDsl

@DomainDsl
public suspend inline fun <R> DomainScope.useCaseRaw(
  crossinline f: suspend UseCaseScope.() -> R,
): Either<Throwable, R> =
  Either.catch {
    withContext(Dispatchers.IO) {
      useCaseManager.useCaseScope.f()
    }
  }

@DomainDsl
public suspend inline fun <R> DomainScope.useCase(
  crossinline f: suspend UseCaseScope.() -> Either<Throwable, R>,
): Either<Throwable, R> =
  useCaseRaw(f)
    .flatten()

@DomainDsl
public inline fun <R> DomainScope.useCaseFlow(
  f: UseCaseScope.() -> Flow<R>,
): Flow<R> =
  useCaseManager.useCaseScope
    .f()
    .flowOn(Dispatchers.IO)
