package dev.kioba.network.user.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class UserLoginResponse(
  @Json(name = "uuid")
  val uuid: String,
  @Json(name = "username")
  val username: String,
  @Json(name = "password")
  val password: String,
  @Json(name = "md5")
  val md5: String,
  @Json(name = "sha1")
  val sha1: String,
  @Json(name = "registered")
  val registered: String,
)