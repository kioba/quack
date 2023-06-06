package io.github.kioba.placeholder.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.github.kioba.placeholder.network.model.Address
import io.github.kioba.placeholder.network.model.Company

@JsonClass(generateAdapter = true)
data class NetworkUser(
  @Json(name = "address")
  val address: Address,
  @Json(name = "company")
  val company: Company,
  @Json(name = "email")
  val email: String,
  @Json(name = "id")
  val id: Int,
  @Json(name = "name")
  val name: String,
  @Json(name = "phone")
  val phone: String,
  @Json(name = "username")
  val username: String,
  @Json(name = "website")
  val website: String
)
