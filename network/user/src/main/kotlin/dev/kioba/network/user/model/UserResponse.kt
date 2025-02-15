package dev.kioba.network.user.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class UserResponse(
  @Json(name = "id")
  val id: Long,
  @Json(name = "firstname")
  val firstname: String,
  @Json(name = "lastname")
  val lastname: String,
  @Json(name = "email")
  val email: String,
  @Json(name = "birthDate")
  val birthDate: String,

  @Json(name = "address")
  val address: AddressResponse,
  @Json(name = "company")
  val company: CompanyResponse,

  @Json(name = "phone")
  val phone: String,
  @Json(name = "login")
  val login: UserLoginResponse,
  @Json(name = "website")
  val website: String,
)

