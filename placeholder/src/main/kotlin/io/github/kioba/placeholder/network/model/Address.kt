package io.github.kioba.placeholder.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.github.kioba.network.user.model.GeoResponse

@JsonClass(generateAdapter = true)
data class Address(
  @Json(name = "city")
  val city: String,
  @Json(name = "geo")
  val geo: GeoResponse,
  @Json(name = "street")
  val street: String,
  @Json(name = "suite")
  val suite: String,
  @Json(name = "zipcode")
  val zipcode: String
)
