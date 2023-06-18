package io.github.kioba.platform.domain

import arrow.core.Either
import arrow.core.extensions.either.monad.flatten
import arrow.core.left
import arrow.core.right
import io.github.kioba.platform.database.DatabaseScope
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


public fun buildDomain(
  databaseScope: DatabaseScope,
): DomainScope =
  object : DomainScope {
    override val useCaseManager: UseCaseManager = buildUseCaseManager(databaseScope)
  }

public interface DomainScope {
  public val useCaseManager: UseCaseManager
}

context(DomainScope)
  public inline fun <R> useCaseRaw(
  f: UseCaseScope.() -> R,
): Either<Throwable, R> =
  useCaseManager.useCaseScope
    .runCatching(f)
    .fold(Either.Companion::right, Either.Companion::left)

context(DomainScope)
  public inline fun <R> useCase(
  f: UseCaseScope.() -> Either<Throwable, R>,
): Either<Throwable, R> =
  useCaseRaw(f)
    .flatten()

context(DomainScope)
  public inline fun <R> useCaseFlow(
  f: UseCaseScope.() -> Flow<R>,
): Flow<Either<Throwable, R>> =
  useCaseManager.useCaseScope.f()
    .map { it.right() as Either<Throwable, R> }
    .catch { emit(it.left()) }

context(DomainScope)
  public inline fun <R> useCaseFlowable(
  f: UseCaseScope.() -> Flowable<R>,
): Flowable<Either<Throwable, R>> =
  useCaseManager.useCaseScope.f()
    .map { it.right() as Either<Throwable, R> }
    .onErrorReturn { it.left() }