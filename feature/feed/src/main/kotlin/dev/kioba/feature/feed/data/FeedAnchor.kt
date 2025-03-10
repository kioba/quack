package dev.kioba.feature.feed.data

import arrow.core.raise.either
import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Created
import dev.kioba.anchor.Effect
import dev.kioba.anchor.RememberAnchorScope
import dev.kioba.anchor.SubscriptionsScope
import dev.kioba.domain.post.api.syncPosts
import dev.kioba.domain.user.api.syncUsers
import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.buildEffectContext
import dev.kioba.platform.network.NetworkScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest


internal typealias FeedAnchor = Anchor<FeedEffects, FeedState>

public class FeedEffects(
  databaseScope: DatabaseScope,
  networkScope: NetworkScope,
) : EffectContext by buildEffectContext(databaseScope, networkScope), Effect

internal fun RememberAnchorScope.feedAnchor(
  effectsScope: FeedEffects,
): FeedAnchor =
  create(
    initialState = ::FeedState,
    effectScope = { effectsScope },
    init = FeedAnchor::init,
  ) {
    listen(::onCreated)
  }

internal suspend fun FeedAnchor.init() {
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

@OptIn(ExperimentalCoroutinesApi::class)
internal fun SubscriptionsScope<FeedEffects, FeedState>.onCreated(
  events: Flow<Created>,
): Flow<List<CombinedFeedItem>> =
  events.flatMapLatest { effect.combined() }
    .anchor(FeedAnchor::updateFeed)

internal fun FeedAnchor.updateFeed(
  posts: List<CombinedFeedItem>,
) {
  reduce { fetchFeedSucceeded(posts) }
}

internal fun FeedAnchor.dismissFeedError() {
  reduce { copy(feedError = null) }
}

internal fun FeedAnchor.dismissUserError() {
  reduce { copy(userError = null) }
}
