package dev.kioba.feature.feed.data

import dev.kioba.anchor.Anchor
import dev.kioba.anchor.AnchorOf
import dev.kioba.anchor.Created
import dev.kioba.anchor.Effect
import dev.kioba.anchor.anchorScope
import dev.kioba.anchor.effect
import dev.kioba.anchor.reduce
import dev.kioba.domain.post.api.syncPosts
import dev.kioba.domain.user.api.syncUsers
import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.domain.DomainScope
import dev.kioba.platform.domain.buildDomain


internal typealias FeedAnchor = Anchor<FeedEffects, FeedState>

public class FeedEffects(
  databaseScope: DatabaseScope,
) : DomainScope by buildDomain(databaseScope), Effect

internal fun feedAnchor(
  effectsScope: FeedEffects,
): FeedAnchor =
  FeedAnchor(
    initialState = ::FeedState,
    effectScope = { effectsScope },
    init = ::init,
  ) {
    listen<Created> {
      effect.combined()
        .anchor(::updateFeed)
    }
  }

internal fun init(): AnchorOf<FeedAnchor> =
  anchorScope {
    reduce { copy(feedLoading = true) }
    effect { syncPosts() }
    effect { syncUsers() }
  }

internal fun updateFeed(
  posts: List<CombinedFeedItem>,
): AnchorOf<FeedAnchor> =
  anchorScope {
    reduce { loadedSuccessfully(posts) }
  }
