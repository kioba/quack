package dev.kioba.platform.domain

import arrow.core.Either
import arrow.core.flatten
import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.network.NetworkScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


public fun buildEffectContext(
  databaseScope: DatabaseScope,
  networkScope: NetworkScope,
): EffectContext =
  object : EffectContext {
    override val useCaseManager: UseCaseManager = buildUseCaseManager(databaseScope, networkScope)
  }

public interface EffectContext {
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
public suspend inline fun <R> EffectContext.useCaseRaw(
  crossinline f: suspend UseCaseScope.() -> R,
): Either<Throwable, R> =
  Either.catch {
    withContext(Dispatchers.IO) {
      useCaseManager.useCaseScope.f()
    }
  }

@DomainDsl
public suspend inline fun <R> EffectContext.useCase(
  crossinline f: suspend UseCaseScope.() -> Either<Throwable, R>,
): Either<Throwable, R> =
  useCaseRaw(f)
    .flatten()

@DomainDsl
public inline fun <R> EffectContext.useCaseFlow(
  f: UseCaseScope.() -> Flow<R>,
): Flow<R> =
  useCaseManager.useCaseScope
    .f()
    .flowOn(Dispatchers.IO)
