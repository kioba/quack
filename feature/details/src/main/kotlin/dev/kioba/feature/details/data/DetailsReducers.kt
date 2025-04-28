package dev.kioba.feature.details.data

import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.post.api.model.Comment
import dev.kioba.domain.post.api.model.Post
import dev.kioba.feature.details.model.CommentUserViewState
import dev.kioba.feature.details.model.CommentViewState
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.feature.details.model.PostAndUserViewState
import dev.kioba.feature.details.model.PostViewState
import dev.kioba.feature.details.model.UserViewState

internal fun DetailsViewState.hideError(): DetailsViewState =
  copy(
    error = null,
  )


internal fun DetailsViewState.updatePostAndUser(
  post: Post,
  user: User,
): DetailsViewState =
  copy(
    isPostLoading = false,
    content = content.copy(
      postAndUser = PostAndUserViewState(
        UserViewState(
          avatar = user.avatar.value,
          name = user.name.value,
        ),
        post = PostViewState(
          title = post.title,
          body = post.body,
        ),
      ),
    )
  )

internal fun DetailsViewState.updateComments(
  comments: List<Comment>,
): DetailsViewState =
  copy(
    areCommentsLoading = false,
    content = content.copy(
      comments = comments.map { it.toViewState() },
    )
  )

private fun Comment.toViewState(): CommentViewState =
  CommentViewState(
    id = id.value,
    user = CommentUserViewState(
      email = userEmail.value,
      name = userName.value,
    ),
    body = body,
  )
