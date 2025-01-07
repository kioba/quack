package dev.kioba.feature.feed

import dev.kioba.anchor.test.runAnchorTest
import dev.kioba.anchor.test.scopes.AnchorTestScope
import dev.kioba.feature.feed.data.FeedEffects
import dev.kioba.feature.feed.data.init
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.platform.network.NetworkScope
import io.mockk.mockk
import org.junit.Test

internal typealias FeedAnchorTestScope = AnchorTestScope<FeedEffects, FeedState>

class FeedAnchorTest {
  @Test
  fun test() =
    runAnchorTest<FeedEffects, FeedState> {
      val networkScope: NetworkScope = mockk()
      given("postApi") {
        initialState { FeedState() }
        effectScope { FeedEffects(mockk(), networkScope) }
      }

      on("initial called", ::init)

      verify("loading state and data fetch") {
        assertState { copy(feedLoading = true) }

      }
    }
}
