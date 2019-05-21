package io.github.kioba.detail.mvi_models

import io.github.kioba.placeholder.network.network_models.Post

sealed class DetailIntent
data class InitialDetailIntent(val post: Post) : DetailIntent()
