package io.github.kioba.domain.post.api.converter

import io.github.kioba.domain.placeholder.user.model.UserId
import io.github.kioba.domain.post.api.model.Post
import io.github.kioba.domain.post.api.model.PostId
import io.github.kioba.network.post.model.PostResponse
import io.github.kioba.persistence.PostEntity

internal fun PostResponse.toDomain(): Post =
  Post(
    id = PostId(id),
    userId = UserId(userId),
    body = body,
    title = title,
  )

internal fun PostEntity.toDomain(): Post =
  Post(
    id = PostId(id),
    userId = UserId(userId),
    body = body,
    title = title,
  )

internal fun Post.toEntity(): PostEntity =
  PostEntity(
    id = id.value,
    userId = userId.value,
    body = body,
    title = title,
  )
