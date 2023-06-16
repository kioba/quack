package io.github.kioba.persistence.user

import io.github.kioba.persistence.UserDB
import io.github.kioba.persistence.UserEntity
import io.github.kioba.persistence.UserEntityQueries
import io.github.kioba.platform.database.DatabaseScope

context(DatabaseScope)
  private fun <R> userDao(
  f: UserEntityQueries.() -> R,
): R =
  createDatabase(UserDB.Schema, UserDB::invoke)
    .userEntityQueries
    .f()

context(DatabaseScope)
  public fun readUsers(): List<UserEntity> =
  userDao { selectAll().executeAsList() }

context(DatabaseScope)
  public fun insertUser(
  user: UserEntity,
): Unit =
  userDao { insertUser(user) }

context(DatabaseScope)
  public fun insertUsers(
  users: List<UserEntity>,
): Unit =
  userDao {
    transaction {
      users.forEach { entity -> insertUser(entity) }
    }
  }
