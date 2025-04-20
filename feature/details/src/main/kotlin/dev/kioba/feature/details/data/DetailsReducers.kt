package dev.kioba.feature.details.data

import dev.kioba.domain.post.api.model.Post
import dev.kioba.feature.details.model.DetailsContentViewState
import dev.kioba.feature.details.model.DetailsViewState

internal fun DetailsViewState.hideLoading(): DetailsViewState =
  copy(
    isLoading = false,
  )

internal fun DetailsViewState.hideError(): DetailsViewState =
  copy(
    error = null,
  )


internal fun DetailsViewState.withPost(
  post: Post,
): DetailsViewState =
  copy(
    content = DetailsContentViewState(
      title = post.title,
      body = post.body,
    )
  )