package dev.kioba.domain.user.api.converter

import dev.kioba.domain.placeholder.user.model.Avatar
import dev.kioba.domain.placeholder.user.model.Email
import dev.kioba.domain.placeholder.user.model.Name
import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.placeholder.user.model.UserId
import dev.kioba.domain.placeholder.user.model.UserName
import dev.kioba.network.user.model.UserResponse
import dev.kioba.persistence.UserEntity

internal fun UserResponse.toDomain(): User =
  User(
    id = UserId(id),
    username = UserName(username),
    name = Name(name),
    email = Email(email),
    avatar = Avatar("https://xsgames.co/randomusers/assets/avatars/male/5.jpg"),
  )


internal fun UserEntity.toDomain(): User =
  User(
    id = UserId(id),
    username = UserName(username),
    name = Name(name),
    email = Email(email),
    avatar = Avatar("https://xsgames.co/randomusers/assets/avatars/male/5.jpg"),
  )

internal fun User.toEntity(): UserEntity =
  UserEntity(
    id = id.value,
    username = username.value,
    name = name.value,
    email = email.value,
    avatar = avatar.value,
  )
