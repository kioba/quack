package io.github.kioba.feed.data

import android.util.Log
import dev.kioba.anchor.AnchorScope
import dev.kioba.anchor.anchorScope
import dev.kioba.anchor.dsl.listen
import dev.kioba.anchor.dsl.reduce
import io.github.kioba.feed.model.CombinedFeedItem
import io.github.kioba.feed.model.FeedState
import io.github.kioba.placeholder.PlaceholderSdk
import io.github.kioba.placeholder.EffectScope
import io.github.kioba.placeholder.buildEffects
import io.github.kioba.placeholder.persistence.DaoScope
import io.github.kioba.platform.database.DatabaseScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow


internal typealias FeedScope = AnchorScope<FeedState, FeedEffectsScope>

public class FeedEffectsScope(
  val sdk: PlaceholderSdk,
  databaseScope: DatabaseScope,
) : EffectScope by buildEffects(databaseScope)

internal fun feedScope(
  effectsScope: FeedEffectsScope,
): FeedScope =
  anchorScope(
    initialState = { FeedState() },
    effectScope = { effectsScope },
    init = { init() },
  ) {
    listen {
      sdk.getFeed()
        .asFlow()
        .combine(
          sdk.getUsers()
            .asFlow()
            .map { it.associateBy { user -> user.id } }
        ) { posts, userMap ->
          posts.map { post ->
            userMap[post.userId]
              .let { user -> CombinedFeedItem(post, user, user?.avatar) }
          }
        }
        .catch { Log.e("FeedError", it.toString()) }
    }
      .anchor<FeedScope, List<CombinedFeedItem>> { posts -> feedAnchor(posts) }
  }

context(FeedScope)
  internal fun init() {
  reduce { copy(feedLoading = true) }
}

context(FeedScope)
  internal fun feedAnchor(
  posts: List<CombinedFeedItem>,
) {
  reduce { loadedSuccessfully(posts) }
}
