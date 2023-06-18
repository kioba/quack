package io.github.kioba.placeholder.user

import io.github.kioba.network.user.model.UserResponse

internal fun UserResponse.toDatabase(): DatabaseUser =
  DatabaseUser(
    email = email,
    id = id,
    name = name,
    phone = phone,
    username = username,
    website = website
  )

internal fun DatabaseUser.toModel(): User =
  User(
    email = email,
    id = id.toLong(),
    name = name,
    username = username,
    avatar = "https://xsgames.co/randomusers/assets/avatars/male/5.jpg"
  )
