package io.github.kioba.domain.placeholder.user.model

public data class User(
  val id: UserId,
  val username: UserName,
  val name: Name,
  val email: Email,
  val avatar: Avatar,
)