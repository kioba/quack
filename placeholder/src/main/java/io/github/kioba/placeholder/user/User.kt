package io.github.kioba.placeholder.user

data class User(
  val id: Int,
  val username: String,
  val name: String,
  val email: String,
  val avatar: String
) {
  companion object
}
