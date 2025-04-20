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
  val title: String,
  val body: String,
)
