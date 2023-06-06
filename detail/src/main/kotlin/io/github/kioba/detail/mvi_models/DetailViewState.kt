package io.github.kioba.detail.mvi_models

import io.github.kioba.placeholder.network.network_models.Comment
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User

data class DetailViewState(
  val post: Post? = null,

  val isCommentsLoading: Boolean = true,
  val commentError: Throwable? = null,
  val comments: List<Comment> = listOf(),

  val isUserLoading: Boolean = true,
  val userError: Throwable? = null,
  val user: User? = null
)
