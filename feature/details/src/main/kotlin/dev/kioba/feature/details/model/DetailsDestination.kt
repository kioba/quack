package dev.kioba.feature.details.model

import dev.kioba.domain.post.api.model.PostId
import dev.kioba.platform.android.compose.navigation.NavigationDestination
import kotlinx.serialization.Serializable

@Serializable
public data class DetailsDestination(
  val postId: PostId,
) : NavigationDestination
