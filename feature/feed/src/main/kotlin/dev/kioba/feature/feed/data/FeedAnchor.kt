package dev.kioba.feature.feed.data

import arrow.core.raise.either
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
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.buildEffectContext
import dev.kioba.platform.network.NetworkScope


internal typealias FeedAnchor = Anchor<FeedEffects, FeedState>

public class FeedEffects(
  databaseScope: DatabaseScope,
  networkScope: NetworkScope,
) : EffectContext by buildEffectContext(databaseScope, networkScope), Effect

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
    either {
      effect {
        syncPosts()
          .onLeft { throwable -> reduce { fetchFeedFailed(throwable) } }
          .bind()
      }
      effect {
        syncUsers()
          .onLeft { throwable -> reduce { fetchUserFailed(throwable) } }
          .bind()
      }
    }
  }

internal fun updateFeed(
  posts: List<CombinedFeedItem>,
): AnchorOf<FeedAnchor> =
  anchorScope {
    reduce { fetchFeedSucceeded(posts) }
  }
