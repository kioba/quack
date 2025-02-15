package dev.kioba.domain.post.api.model

import dev.kioba.domain.placeholder.user.model.UserId

public data class Post(
  val content: String,
  val id: PostId,
  val title: String,
  val userId: UserId,
)
