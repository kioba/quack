package io.github.kioba.placeholder.converter

import io.github.kioba.network.user.model.UserResponse
import io.github.kioba.persistence.UserEntity
import io.github.kioba.placeholder.user.User
import io.github.kioba.platform.database.EntityConverter
import io.github.kioba.platform.network.NetworkConverter

internal object UserListConverter :
  NetworkConverter<List<User>, List<UserResponse>>,
  EntityConverter<List<User>, List<UserEntity>> {
  override fun List<UserResponse>.fromNetworkToDomain(): List<User> =
    with(UserConverter) {
      map { it.fromNetworkToDomain() }
    }

  override fun List<UserEntity>.fromEntityToDomain(): List<User> =
    with(UserConverter) {
      map { it.fromEntityToDomain() }
    }

  override fun List<User>.fromDomainToEntity(): List<UserEntity> =
    with(UserConverter) {
      map { it.fromDomainToEntity() }
    }
}