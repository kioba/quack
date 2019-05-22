package io.github.kioba.detail.mvi_models

import io.github.kioba.placeholder.post.Post

sealed class DetailIntent
data class InitialDetailIntent(val post: Post) : DetailIntent()
