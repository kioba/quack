package dev.kioba.persistence.user

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import dev.kioba.persistence.UserEntity
import dev.kioba.persistence.UserEntityQueries
import dev.kioba.platform.database.DatabaseScope
import io.github.kioba.persistence.UserDB
import kotlinx.coroutines.Dispatchers
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
  userDao { selectById(id).executeAsOneOrNull() }

public fun DatabaseScope.streamUsers(): Flow<List<UserEntity>> =
  userDao { selectAll().asFlow().mapToList(Dispatchers.IO) }

public fun DatabaseScope.streamUser(
  id: Long,
): Flow<UserEntity> =
  userDao {
    selectById(id)
      .asFlow()
      .mapToOne(context = Dispatchers.IO)
  }


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
