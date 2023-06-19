package io.github.kioba.domain.post.api.model

import io.github.kioba.domain.placeholder.user.model.UserId

public data class Post(
  val body: String,
  val id: PostId,
  val title: String,
  val userId: UserId,
)
