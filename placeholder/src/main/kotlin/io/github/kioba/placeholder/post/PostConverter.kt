package io.github.kioba.placeholder.post

internal fun NetworkPost.toDatabase(): DatabasePost =
  DatabasePost(
    id = id,
    body = body,
    title = title,
    userId = userId
  )

internal fun DatabasePost.toModel(): Post =
  Post(
    id = id,
    body = body,
    title = title,
    userId = userId
  )
