package dev.kioba.feature.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.kioba.domain.post.fakes.givenCommentBody
import dev.kioba.domain.post.fakes.givenCommentId
import dev.kioba.domain.post.fakes.givenCommentIdOne
import dev.kioba.domain.post.fakes.givenCommentIdTwo
import dev.kioba.domain.post.fakes.givenCommentUserEmail
import dev.kioba.domain.post.fakes.givenCommentUserEmailOne
import dev.kioba.domain.post.fakes.givenCommentUserName
import dev.kioba.domain.post.fakes.givenCommentUserNameOne
import dev.kioba.domain.user.fakes.givenAvatar
import dev.kioba.domain.user.fakes.givenName
import dev.kioba.domain.user.fakes.givenPostBody
import dev.kioba.domain.user.fakes.givenPostTitle
import dev.kioba.feature.details.model.CommentUserViewState
import dev.kioba.feature.details.model.CommentViewState
import dev.kioba.feature.details.model.DetailsContentViewState
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.feature.details.model.PostAndUserViewState
import dev.kioba.feature.details.model.PostViewState
import dev.kioba.feature.details.model.UserViewState
import dev.kioba.platform.test.TestDataScope
import dev.kioba.platform.test.testDataProvider

internal class DetailsPreview : PreviewParameterProvider<DetailsViewState> {
  override val values: Sequence<DetailsViewState> =
    sequence {
      testDataProvider {
        yield(defaultState())
      }
    }

  private fun TestDataScope.defaultState() =
    DetailsViewState(
      isPostLoading = false,
      error = null,
      content = DetailsContentViewState(
        postAndUser = PostAndUserViewState(
          user = UserViewState(
            avatar = givenAvatar().value,
            name = givenName().value,
          ),
          post = PostViewState(
            title = givenPostTitle(),
            body = givenPostBody(),
          ),
        ),
        comments = listOf(
          givenCommentViewState(),
          givenCommentViewStateOne(),
          givenCommentViewStateTwo(),
        ),
      ),
    )
}

private fun TestDataScope.givenCommentViewState(): CommentViewState =
  CommentViewState(
    id = givenCommentId().value,
    user = givenCommentUserViewState(),
    body = givenCommentBody(),
  )

private fun TestDataScope.givenCommentUserViewState(): CommentUserViewState =
  CommentUserViewState(
    email = givenCommentUserEmail().value,
    name = givenCommentUserName().value,
  )

private fun TestDataScope.givenCommentViewStateOne(): CommentViewState =
  CommentViewState(
    id = givenCommentIdOne().value,
    user = givenCommentUserViewStateOne(),
    body = givenCommentBody(),
  )

private fun TestDataScope.givenCommentUserViewStateOne(): CommentUserViewState =
  CommentUserViewState(
    email = givenCommentUserEmailOne().value,
    name = givenCommentUserNameOne().value,
  )

private fun TestDataScope.givenCommentViewStateTwo(): CommentViewState =
  CommentViewState(
    id = givenCommentIdTwo().value,
    user = givenCommentUserViewStateTwo(),
    body = givenCommentBody(),
  )

private fun TestDataScope.givenCommentUserViewStateTwo(): CommentUserViewState =
  CommentUserViewState(
    email = givenCommentUserEmailOne().value,
    name = givenCommentUserNameOne().value,
  )
