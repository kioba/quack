package dev.kioba.network.post.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.datetime.Instant

@JsonClass(generateAdapter = true)
public data class PostResponse(
  @Json(name = "id")
  val id: Long,
  @Json(name = "slug")
  val slug: String,
  @Json(name = "url")
  val url: String,
  @Json(name = "title")
  val title: String,
  @Json(name = "content")
  val content: String,
  @Json(name = "image")
  val image: String,
  @Json(name = "thumbnail")
  val thumbnail: String,
  @Json(name = "status")
  val status: String,
  @Json(name = "category")
  val category: String,
  @Json(name = "publishedAt")
  val publishedAt: String,
  @Json(name = "updatedAt")
  val updatedAt: String,
  @Json(name = "userId")
  val userId: Long,
)