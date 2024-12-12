package dev.kioba.feature.feed.model

import dev.kioba.anchor.ViewState
import dev.kioba.domain.placeholder.user.model.Avatar
import dev.kioba.domain.placeholder.user.model.Email
import dev.kioba.domain.placeholder.user.model.Name
import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.placeholder.user.model.UserId
import dev.kioba.domain.placeholder.user.model.UserName
import dev.kioba.domain.post.api.model.Post


internal data class FeedState(
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
) : ViewState

internal data class CombinedFeedItem(
  val post: Post,
  val user: User?,
  val avatar: String?,
)
