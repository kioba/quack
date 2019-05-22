package io.github.kioba.placeholder.post

data class NetworkPost(
  val body: String,
  val id: Int,
  val title: String,
  val userId: Int
) {
  companion object
}
