package io.github.kioba.domain.user.api.converter

import io.github.kioba.domain.placeholder.user.model.Avatar
import io.github.kioba.domain.placeholder.user.model.Email
import io.github.kioba.domain.placeholder.user.model.Name
import io.github.kioba.domain.placeholder.user.model.User
import io.github.kioba.domain.placeholder.user.model.UserId
import io.github.kioba.domain.placeholder.user.model.UserName
import io.github.kioba.network.user.model.UserResponse
import io.github.kioba.persistence.UserEntity

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
