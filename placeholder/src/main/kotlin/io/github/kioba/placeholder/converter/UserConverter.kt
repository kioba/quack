package io.github.kioba.placeholder.converter

import io.github.kioba.network.user.model.UserResponse
import io.github.kioba.persistence.UserEntity
import io.github.kioba.placeholder.user.User
import io.github.kioba.platform.database.EntityConverter
import io.github.kioba.platform.network.NetworkConverter

object UserConverter :
  NetworkConverter<User, UserResponse>,
  EntityConverter<User, UserEntity> {
  override fun UserResponse.fromNetworkToDomain(): User =
    User(
      id = id.toLong(),
      username = username,
      name = name,
      email = email,
      avatar = "https://xsgames.co/randomusers/assets/avatars/male/5.jpg",
    )

  override fun UserEntity.fromEntityToDomain(): User =
    User(
      id = id,
      username = username,
      name = name,
      email = email,
      avatar = avatar,
    )

  override fun User.fromDomainToEntity(): UserEntity =
    UserEntity(
      id = id,
      username = username,
      name = name,
      email = email,
      avatar = avatar,
    )
}