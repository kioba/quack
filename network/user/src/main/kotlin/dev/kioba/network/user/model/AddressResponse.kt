package dev.kioba.network.user.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class AddressResponse(
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
