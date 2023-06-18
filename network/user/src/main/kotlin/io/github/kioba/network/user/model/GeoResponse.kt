package io.github.kioba.network.user.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class GeoResponse(
  @Json(name = "lat")
  val lat: String,
  @Json(name = "lng")
  val lng: String,
)
