package dev.kioba.feature.feed

import arrow.core.Either


internal fun syncPostsError(): Either<Throwable, Nothing> =
  Either.Left(GenericApiError("Sync Posts failed"))

internal fun syncUsersError(): Either<Throwable, Nothing> =
  Either.Left(GenericApiError("Sync Users failed"))

internal data class GenericApiError(
  override val message: String?,
) : Throwable()