package dev.kioba.feature.details.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.kioba.domain.user.fakes.givenAvatar
import dev.kioba.domain.user.fakes.givenName
import dev.kioba.domain.user.fakes.givenPostBody
import dev.kioba.domain.user.fakes.givenPostTitle
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
        comments = emptyList(),
      ),
    )
}