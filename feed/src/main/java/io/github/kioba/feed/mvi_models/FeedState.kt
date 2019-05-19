package io.github.kioba.feed.mvi_models

import io.github.kioba.placeholder.json_placeholder.network_models.Post


data class FeedState(
  val loading: Boolean = true,
  val feed: List<Post> = listOf(),
  val error: Throwable? = null
)
