package io.github.kioba.placeholder.post

data class Post(
  val body: String,
  val id: Int,
  val title: String,
  val userId: Long
) {
  companion object
}
