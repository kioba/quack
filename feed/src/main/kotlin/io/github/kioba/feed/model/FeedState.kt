package io.github.kioba.feed.model

import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User


data class FeedState(
  val user: User = User(
    id = -1,
    username = "scarlett_Y",
    name = "Scarlett Ywett",
    avatar = "https://xsgames.co/randomusers/assets/avatars/female/5.jpg",
    email = "Scarlett@example.com"
  ),
  val feedLoading: Boolean = true,
  val usersLoading: Boolean = true,
  val feed: List<Post> = listOf(),
  val feedError: Throwable? = null,
  val users: Map<Long, User> = mapOf(),
  val userError: Throwable? = null,
  val combined: List<CombinedFeedItem> = listOf(),
)

data class CombinedFeedItem(
  val post: Post,
  val user: User?,
  val avatar: String?,
)
