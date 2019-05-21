package io.github.kioba.detail.mvi_models

import io.github.kioba.placeholder.json_placeholder.network_models.Post

sealed class DetailIntent
data class InitialDetailIntent(val post: Post) : DetailIntent()
