package io.github.kioba.feed.mvi_models

import io.github.kioba.placeholder.json_placeholder.network_models.Post

sealed class FeedResult
data class FeedSuccess(val value: List<Post>) : FeedResult()
data class FeedError(val error: Throwable) : FeedResult()
object FeedLoading : FeedResult()
