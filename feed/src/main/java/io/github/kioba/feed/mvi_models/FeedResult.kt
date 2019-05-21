package io.github.kioba.feed.mvi_models

import io.github.kioba.placeholder.network.network_models.Post
import io.github.kioba.placeholder.user.User

sealed class FeedResult
data class FeedContentSuccess(val value: List<Post>) : FeedResult()
data class FeedContentError(val error: Throwable) : FeedResult()
object FeedContentLoading : FeedResult()

data class FeedUserSuccess(val value: List<User>) : FeedResult()
data class FeedUserError(val error: Throwable) : FeedResult()
object FeedUserLoading : FeedResult()
