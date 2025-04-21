package dev.kioba.feature.details.data

import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.post.api.model.Post
import dev.kioba.feature.details.model.DetailsContentViewState
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.feature.details.model.PostViewState
import dev.kioba.feature.details.model.UserViewState

internal fun DetailsViewState.hideLoading(): DetailsViewState =
  copy(
    isLoading = false,
  )

internal fun DetailsViewState.hideError(): DetailsViewState =
  copy(
    error = null,
  )


internal fun DetailsViewState.initWithPostAndUser(
  post: Post,
  user: User,
): DetailsViewState =
  copy(
    content = DetailsContentViewState(
      UserViewState(
        avatar = user.avatar.value,
        name = user.name.value,
      ),
      post = PostViewState(
        title = post.title,
        body = post.body,
      ),
    )
  )
