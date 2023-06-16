package io.github.kioba.placeholder.converter

import io.github.kioba.persistence.UserEntity
import io.github.kioba.placeholder.EntityConverter
import io.github.kioba.placeholder.NetworkConverter
import io.github.kioba.placeholder.user.User
import io.github.kioba.placeholder.user.UserNetwork

object UserConverter :
  NetworkConverter<User, UserNetwork>,
  EntityConverter<User, UserEntity> {
  override fun UserNetwork.fromNetworkToDomain(): User =
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