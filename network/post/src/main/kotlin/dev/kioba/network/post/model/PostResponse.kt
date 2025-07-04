package dev.kioba.network.post.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public data class PostResponse(
  @Json(name = "body")
  val body: String,
  @Json(name = "id")
  val id: Long,
  @Json(name = "title")
  val title: String,
  @Json(name = "userId")
  val userId: Long,
)

@JsonClass(generateAdapter = true)
public data class CommentResponse(
  @Json(name = "postId")
  val postId: Long,
  @Json(name = "id")
  val id: Long,
  @Json(name = "name")
  val name: String,
  @Json(name = "email")
  val email: String,
  @Json(name = "body")
  val body: String,
)
