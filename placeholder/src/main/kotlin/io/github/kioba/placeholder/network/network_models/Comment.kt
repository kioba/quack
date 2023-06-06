package io.github.kioba.placeholder.network.network_models

data class Comment(
  val body: String,
  val email: String,
  val id: Int,
  val name: String,
  val postId: Int
) {
  companion object
}
