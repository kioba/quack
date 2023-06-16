package io.github.kioba.placeholder.user

data class User(
  val id: Long,
  val username: String,
  val name: String,
  val email: String,
  val avatar: String
) {
  companion object
}
