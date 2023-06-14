package io.github.kioba.feed.data

import arrow.core.toOption
import io.github.kioba.feed.model.CombinedFeedItem
import io.github.kioba.feed.model.FeedState
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User

internal fun FeedState.loadedSuccessfully(
  posts: List<Post>,
): FeedState =
  copy(
    feedLoading = false,
    feed = posts,
    feedError = null,
    combined = createCombined(posts, users)
  )

internal fun createCombined(
  feed: List<Post>,
  userMap: Map<Int, User>,
): List<CombinedFeedItem> =
  feed.map { post ->
    val user = userMap[post.userId].toOption()
    CombinedFeedItem(
      post,
      user = user.orNull(),
      avatar = user.map { it.avatar }.orNull(),
    )
  }
