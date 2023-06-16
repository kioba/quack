package io.github.kioba.placeholder.converter

import io.github.kioba.persistence.UserEntity
import io.github.kioba.placeholder.EntityConverter
import io.github.kioba.placeholder.NetworkConverter
import io.github.kioba.placeholder.user.User
import io.github.kioba.placeholder.user.UserNetwork

internal object UserListConverter :
  NetworkConverter<List<User>, List<UserNetwork>>,
  EntityConverter<List<User>, List<UserEntity>> {
  override fun List<UserNetwork>.fromNetworkToDomain(): List<User> =
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