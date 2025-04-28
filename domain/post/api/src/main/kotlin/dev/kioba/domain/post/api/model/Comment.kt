package dev.kioba.domain.post.api.model

import dev.kioba.domain.placeholder.user.model.Email
import dev.kioba.domain.placeholder.user.model.UserName

public data class Comment(
  val id: CommentId,
  val postId: PostId,
  val body: String,
  val userEmail: Email,
  val userName: UserName,
)

