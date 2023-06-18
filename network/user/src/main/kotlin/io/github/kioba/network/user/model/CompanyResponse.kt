package io.github.kioba.network.user.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class CompanyResponse(
  @Json(name = "bs")
  val bs: String,
  @Json(name = "catchPhrase")
  val catchPhrase: String,
  @Json(name = "name")
  val name: String
)