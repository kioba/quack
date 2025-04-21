package dev.kioba.feature.details.model

import androidx.compose.runtime.Immutable
import dev.kioba.anchor.ViewState

@Immutable
internal data class DetailsViewState(
  val isLoading: Boolean = true,
  val error: Throwable? = null,
  val content: DetailsContentViewState? = null,
) : ViewState

@Immutable
internal data class DetailsContentViewState(
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