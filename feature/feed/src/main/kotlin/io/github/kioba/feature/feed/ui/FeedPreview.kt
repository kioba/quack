package io.github.kioba.feature.feed.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import io.github.kioba.domain.post.fakes.givenPost
import io.github.kioba.domain.post.fakes.givenPostIdOne
import io.github.kioba.domain.post.fakes.givenPostIdThree
import io.github.kioba.domain.post.fakes.givenPostIdTwo
import io.github.kioba.domain.user.fakes.givenAvatar
import io.github.kioba.domain.user.fakes.givenUser
import io.github.kioba.domain.user.fakes.givenUserId
import io.github.kioba.feature.feed.model.CombinedFeedItem
import io.github.kioba.feature.feed.model.FeedState
import io.github.kioba.platform.test.TestDataScope
import io.github.kioba.platform.test.testDataProvider

class FeedPreview : PreviewParameterProvider<FeedState> {
  override val values: Sequence<FeedState> = sequence {
    testDataProvider {
      yield(defaultState())
    }
  }

  context(TestDataScope)
  private fun defaultState(): FeedState =
    FeedState(
      feedLoading = false,
      feed = listOf(
        givenPost(givenPostIdOne()),
        givenPost(givenPostIdTwo()),
        givenPost(givenPostIdThree()),
      ),
      usersLoading = false,
      users = mapOf(
        givenUserId().value to givenUser(),
      ),
      combined = listOf(
        CombinedFeedItem(
          post = givenPost(givenPostIdOne()),
          user = givenUser(),
          avatar = givenAvatar().value,
        ),
        CombinedFeedItem(
          post = givenPost(givenPostIdTwo()),
          user = givenUser(),
          avatar = givenAvatar().value,
        ),
        CombinedFeedItem(
          post = givenPost(givenPostIdThree()),
          user = givenUser(),
          avatar = givenAvatar().value,
        ),
      ),
    )
}
