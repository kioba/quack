package io.github.kioba.feed.mvi_models

sealed class FeedIntent
object InitialFeedIntent : FeedIntent()
