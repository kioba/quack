package io.github.kioba.feed.mvi_models

sealed class FeedResult
data class FeedSuccess(val value: List<Int>) : FeedResult()
data class FeedError(val error: Throwable) : FeedResult()
object FeedLoading : FeedResult()
