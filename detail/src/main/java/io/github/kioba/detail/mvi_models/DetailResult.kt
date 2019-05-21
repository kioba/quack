package io.github.kioba.detail.mvi_models

import io.github.kioba.placeholder.network.network_models.Comment
import io.github.kioba.placeholder.network.network_models.Post
import io.github.kioba.placeholder.user.User

sealed class DetailResult

data class DetailPostSuccess(val post: Post) : DetailResult()

data class DetailCommentSuccess(val comments: List<Comment>) : DetailResult()
data class DetailCommentError(val error: Throwable) : DetailResult()
object DetailCommentLoading : DetailResult()

data class DetailUserSuccess(val user: User) : DetailResult()
data class DetailUserError(val error: Throwable) : DetailResult()
object DetailUserLoading : DetailResult()
