package dev.kioba.feature.details.model

import android.os.Bundle
import androidx.navigation.NavType
import dev.kioba.domain.post.api.model.PostId
import kotlinx.serialization.json.Json

public object PostIdParameterType : NavType<PostId>(
  isNullableAllowed = false
) {
  override fun get(
    bundle: Bundle,
    key: String,
  ): PostId? =
    bundle.getString(key)
      ?.let { parseValue(it) }

  override fun parseValue(
    value: String,
  ): PostId =
    Json.decodeFromString(value)

  override fun put(
    bundle: Bundle,
    key: String,
    value: PostId,
  ) {
    bundle.putString(key, serializeAsValue(value))
  }

  override fun serializeAsValue(
    value: PostId,
  ): String =
    Json.encodeToString(value)
}