package io.github.kioba.feature.feed.data

import io.github.kioba.domain.placeholder.user.model.User
import io.github.kioba.domain.post.api.listenPosts
import io.github.kioba.domain.post.api.model.Post
import io.github.kioba.domain.user.api.listenUsers
import io.github.kioba.feature.feed.model.CombinedFeedItem
import io.github.kioba.platform.domain.DomainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

fun toCombinedFeedItem(
  posts: List<Post>,
  users: List<User>,
): List<CombinedFeedItem> =
  posts.map {
    val user = users.find { user -> user.id == it.userId }
    CombinedFeedItem(it, user, user?.avatar?.value)
  }

context(DomainScope)
fun combined(): Flow<List<CombinedFeedItem>> =
  listenPosts()
    .combine(listenUsers(), ::toCombinedFeedItem)