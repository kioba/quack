package dev.kioba.feature.feed.data

import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState

internal fun FeedState.fetchFeedSucceeded(
  posts: List<CombinedFeedItem>,
): FeedState =
  copy(
    feedLoading = false,
    usersLoading = false,
    feed = posts.map { it.post },
    feedError = null,
    users = posts.mapNotNull { it.user }.associateBy { it.id.value },
    combined = posts
  )

internal fun FeedState.fetchFeedFailed(
  throwable: Throwable,
): FeedState =
  copy(
    feedLoading = false,
    usersLoading = false,
    feedError = throwable,
  )

internal fun FeedState.fetchUserFailed(
  throwable: Throwable,
): FeedState =
  copy(
    feedLoading = false,
    usersLoading = false,
    userError = throwable,
  )

