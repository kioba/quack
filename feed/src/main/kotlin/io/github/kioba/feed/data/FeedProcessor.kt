package io.github.kioba.feed.data

import dev.kioba.anchor.AnchorScope
import dev.kioba.anchor.anchorScope
import dev.kioba.anchor.dsl.effect
import dev.kioba.anchor.dsl.listen
import dev.kioba.anchor.dsl.reduce
import io.github.kioba.domain.post.api.syncPosts
import io.github.kioba.domain.user.api.syncUsers
import io.github.kioba.feed.model.CombinedFeedItem
import io.github.kioba.feed.model.FeedState
import io.github.kioba.platform.database.DatabaseScope
import io.github.kioba.platform.domain.DomainScope
import io.github.kioba.platform.domain.buildDomain


internal typealias FeedScope = AnchorScope<FeedState, FeedEffectsScope>

public class FeedEffectsScope(
  databaseScope: DatabaseScope,
) : DomainScope by buildDomain(databaseScope)

internal fun feedScope(
  effectsScope: FeedEffectsScope,
): FeedScope =
  anchorScope(
    initialState = { FeedState() },
    effectScope = { effectsScope },
    init = { init() },
  ) {
    listen { combined() }
      .anchor<FeedScope, List<CombinedFeedItem>> { updateFeed(it) }
  }

context(FeedScope)
  internal suspend fun init() {
  reduce { copy(feedLoading = true) }
  effect { syncPosts() }
  effect { syncUsers() }
}

context(FeedScope)
  internal fun updateFeed(
  posts: List<CombinedFeedItem>,
) {
  reduce { loadedSuccessfully(posts) }
}
