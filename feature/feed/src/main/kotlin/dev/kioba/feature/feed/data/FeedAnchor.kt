package dev.kioba.feature.feed.data

import arrow.core.raise.either
import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Created
import dev.kioba.anchor.Effect
import dev.kioba.anchor.RememberAnchorScope
import dev.kioba.anchor.SubscriptionsScope
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.domain.post.api.syncPosts
import dev.kioba.domain.user.api.syncUsers
import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.feature.feed.model.DetailsSelectedIntent
import dev.kioba.platform.android.compose.navigation.NavigationContext
import dev.kioba.platform.android.compose.navigation.NavigationIntent
import dev.kioba.platform.android.compose.navigation.buildNavigationContext
import dev.kioba.platform.android.compose.navigation.navigate
import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.buildEffectContext
import dev.kioba.platform.network.NetworkScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest


internal typealias FeedAnchor = Anchor<FeedEffects, FeedState>
internal typealias FeedSubscription = SubscriptionsScope<FeedEffects, FeedState>

public class FeedEffects(
  databaseScope: DatabaseScope,
  networkScope: NetworkScope,
  navFlow: suspend (NavigationIntent) -> Unit,
) :
  EffectContext by buildEffectContext(databaseScope, networkScope),
  NavigationContext by buildNavigationContext(navFlow),
  Effect

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
internal fun FeedSubscription.onCreated(
  events: Flow<Created>,
): Flow<List<CombinedFeedItem>> =
  events.flatMapLatest { effect.listenPostAndUserCombined() }
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

internal suspend fun FeedAnchor.navigateToDetails(
  postId: PostId,
) {
  navigate { DetailsSelectedIntent(postId)  }
}