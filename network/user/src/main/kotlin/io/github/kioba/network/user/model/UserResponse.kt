package io.github.kioba.network.user.model

@com.squareup.moshi.JsonClass(generateAdapter = true)
public data class UserResponse(
  @com.squareup.moshi.Json(name = "address")
  val address: AddressResponse,
  @com.squareup.moshi.Json(name = "company")
  val company: CompanyResponse,
  @com.squareup.moshi.Json(name = "email")
  val email: String,
  @com.squareup.moshi.Json(name = "id")
  val id: Int,
  @com.squareup.moshi.Json(name = "name")
  val name: String,
  @com.squareup.moshi.Json(name = "phone")
  val phone: String,
  @com.squareup.moshi.Json(name = "username")
  val username: String,
  @com.squareup.moshi.Json(name = "website")
  val website: String
)