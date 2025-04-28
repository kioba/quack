package dev.kioba.feature.details.model

import androidx.compose.runtime.Immutable
import dev.kioba.anchor.ViewState

@Immutable
internal data class DetailsViewState(
  val isPostLoading: Boolean = true,
  val areCommentsLoading: Boolean = true,
  val error: Throwable? = null,
  val content: DetailsContentViewState = DetailsContentViewState(
    postAndUser = null,
    comments = null,
  ),
) : ViewState

@Immutable
internal data class DetailsContentViewState(
  val postAndUser: PostAndUserViewState?,
  val comments: List<CommentViewState>?,
)

@Immutable
internal data class PostAndUserViewState(
  val user: UserViewState,
  val post: PostViewState,
)

@Immutable
internal data class PostViewState(
  val title: String,
  val body: String,
)

@Immutable
internal data class UserViewState(
  val avatar: String,
  val name: String,
)

@Immutable
internal data class CommentUserViewState(
  val email: String,
  val name: String,
)

@Immutable
internal data class CommentViewState(
  val id: Long,
  val user: CommentUserViewState,
  val body: String,
)