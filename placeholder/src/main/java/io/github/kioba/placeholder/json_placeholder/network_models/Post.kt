package io.github.kioba.placeholder.json_placeholder.network_models

data class Post(
  val body: String,
  val id: Int,
  val title: String,
  val userId: Int
) {
  companion object
}
