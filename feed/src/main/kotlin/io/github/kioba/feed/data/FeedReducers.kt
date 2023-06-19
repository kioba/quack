package io.github.kioba.feed.data

import io.github.kioba.feed.model.CombinedFeedItem
import io.github.kioba.feed.model.FeedState

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

