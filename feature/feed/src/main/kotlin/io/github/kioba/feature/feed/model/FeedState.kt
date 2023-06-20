package io.github.kioba.feature.feed.model

import io.github.kioba.domain.placeholder.user.model.Avatar
import io.github.kioba.domain.placeholder.user.model.Email
import io.github.kioba.domain.placeholder.user.model.Name
import io.github.kioba.domain.placeholder.user.model.User
import io.github.kioba.domain.placeholder.user.model.UserId
import io.github.kioba.domain.placeholder.user.model.UserName
import io.github.kioba.domain.post.api.model.Post


data class FeedState(
  val user: User = User(
    id = UserId(-1),
    username = UserName("scarlett_Y"),
    name = Name("Scarlett Ywett"),
    avatar = Avatar("https://xsgames.co/randomusers/assets/avatars/female/5.jpg"),
    email = Email("Scarlett@example.com"),
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
