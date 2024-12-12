package dev.kioba.domain.post.api.converter

import dev.kioba.domain.placeholder.user.model.UserId
import dev.kioba.domain.post.api.model.Post
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.network.post.model.PostResponse
import dev.kioba.persistence.PostEntity

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
