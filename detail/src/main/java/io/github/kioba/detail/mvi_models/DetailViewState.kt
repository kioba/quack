package io.github.kioba.detail.mvi_models

import io.github.kioba.placeholder.json_placeholder.network_models.Comment
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.github.kioba.placeholder.json_placeholder.network_models.User

data class DetailViewState(
  val post: Post? = null,

  val isCommentsLoading: Boolean = true,
  val commentError: Throwable? = null,
  val comments: List<Comment> = listOf(),

  val isUserLoading: Boolean = true,
  val userError: Throwable? = null,
  val user: User? = null
)