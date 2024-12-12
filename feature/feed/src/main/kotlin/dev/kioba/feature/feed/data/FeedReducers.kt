package dev.kioba.feature.feed.data

import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState

internal fun FeedState.loadedSuccessfully(
  posts: List<CombinedFeedItem>,
): FeedState =
  copy(
    feedLoading = false,
    feed = posts.map { it.post },
    feedError = null,
    users = posts.mapNotNull { it.user }.associateBy { it.id.value },
    combined = posts
  )

