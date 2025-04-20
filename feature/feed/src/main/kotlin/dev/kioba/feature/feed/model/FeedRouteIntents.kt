package dev.kioba.feature.feed.model

import dev.kioba.domain.post.api.model.PostId
import dev.kioba.platform.android.compose.navigation.NavigationIntent

public data class DetailsSelectedIntent(
  val postId: PostId,
) : NavigationIntent

