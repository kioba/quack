package dev.kioba.feature.feed.data

import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.post.api.listenPosts
import dev.kioba.domain.post.api.model.Post
import dev.kioba.domain.user.api.listenUsers
import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.platform.domain.EffectContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal fun toCombinedFeedItem(
  posts: List<Post>,
  users: List<User>,
): List<CombinedFeedItem> =
  posts.map {
    val user = users.find { user -> user.id == it.userId }
    CombinedFeedItem(it, user, user?.avatar?.value)
  }

internal fun EffectContext.listenPostAndUserCombined(): Flow<List<CombinedFeedItem>> =
  listenPosts()
    .combine(listenUsers(), ::toCombinedFeedItem)