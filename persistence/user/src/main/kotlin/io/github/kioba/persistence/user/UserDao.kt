package io.github.kioba.persistence.user

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import io.github.kioba.persistence.UserDB
import io.github.kioba.persistence.UserEntity
import io.github.kioba.persistence.UserEntityQueries
import io.github.kioba.platform.database.DatabaseScope
import kotlinx.coroutines.flow.Flow

private object DB {
  var query: UserEntityQueries? = null
}

private fun <R> DatabaseScope.userDao(
  f: UserEntityQueries.() -> R,
): R =
  if (DB.query == null) {
    DB.query = createDatabase(UserDB.Schema, UserDB::invoke)
      .userEntityQueries
    DB.query!!
  } else {
    DB.query!!
  }.f()

public fun DatabaseScope.readUsers(): List<UserEntity> =
  userDao { selectAll().executeAsList() }

public fun DatabaseScope.readUser(
  id: Long,
): UserEntity? =
  userDao { select(id).executeAsOneOrNull() }

public fun DatabaseScope.streamUsers(): Flow<List<UserEntity>> =
  userDao { selectAll().asFlow().mapToList() }

public fun DatabaseScope.insertUser(
  user: UserEntity,
): Unit =
  userDao { insertUser(user) }

public fun DatabaseScope.insertUsers(
  users: List<UserEntity>,
): Unit =
  userDao {
    transaction {
      users.forEach { entity -> insertUser(entity) }
    }
  }
