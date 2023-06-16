package io.github.kioba.placeholder.post

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkPost(
  @Json(name = "body") val body: String,
  @Json(name = "id") val id: Int,
  @Json(name = "title") val title: String,
  @Json(name = "userId") val userId: Long,
)