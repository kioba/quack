package io.github.kioba.feed.mvi_models

import arrow.core.Option
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.github.kioba.placeholder.json_placeholder.network_models.User


data class FeedState(
  val feedLoading: Boolean = true,
  val feed: List<Post> = listOf(),
  val feedError: Throwable? = null,
  val usersLoading: Boolean = true,
  val users: Map<Int, User> = mapOf(),
  val userError: Throwable? = null,
  val combined: List<CombinedFeed> = listOf()
)

data class CombinedFeed(val post: Post, val user: Option<User>, val avatar: Option<String>)
