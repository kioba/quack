package dev.kioba.feature.feed.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.kioba.domain.post.fakes.givenPost
import dev.kioba.domain.post.fakes.givenPostIdOne
import dev.kioba.domain.post.fakes.givenPostIdThree
import dev.kioba.domain.post.fakes.givenPostIdTwo
import dev.kioba.domain.user.fakes.givenAvatar
import dev.kioba.domain.user.fakes.givenUser
import dev.kioba.domain.user.fakes.givenUserId
import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.platform.test.TestDataScope
import dev.kioba.platform.test.testDataProvider

internal class FeedPreview : PreviewParameterProvider<FeedState> {
  override val values: Sequence<FeedState> = sequence {
    testDataProvider {
      yield(defaultState())
    }
  }

  private fun TestDataScope.defaultState(): FeedState =
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
