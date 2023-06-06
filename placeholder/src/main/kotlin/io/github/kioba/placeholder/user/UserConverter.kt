package io.github.kioba.placeholder.user

internal fun NetworkUser.toDatabase(): DatabaseUser =
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
    id = id,
    name = name,
    username = username,
    avatar = "https://api.adorable.io/avatars/134/$username"
  )
