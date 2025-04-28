package dev.kioba.domain.post.api.model

import kotlinx.serialization.Serializable

@Serializable
public data class PostId(
  public val value: Long,
)