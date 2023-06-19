package io.github.kioba.network.user.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class UserResponse(
  @Json(name = "address")
  val address: AddressResponse,
  @Json(name = "company")
  val company: CompanyResponse,
  @Json(name = "email")
  val email: String,
  @Json(name = "id")
  val id: Long,
  @Json(name = "name")
  val name: String,
  @Json(name = "phone")
  val phone: String,
  @Json(name = "username")
  val username: String,
  @Json(name = "website")
  val website: String,
)