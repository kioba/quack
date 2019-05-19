package io.github.kioba.feed.mvi_models


data class FeedState(
  val loading: Boolean = true,
  val feed: List<Int> = listOf(),
  val error: Throwable? = null
)
