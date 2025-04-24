package dev.kioba.persistence.post

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import dev.kioba.platform.database.DatabaseScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

public fun DatabaseScope.readPosts(): List<PostEntity> =
  postDao { selectAll().executeAsList() }

public fun DatabaseScope.streamPosts(): Flow<List<PostEntity>> =
  postDao {
    selectAll()
      .asFlow()
      .mapToList(context = Dispatchers.IO)
  }

public fun DatabaseScope.streamPost(
  id: Long,
): Flow<PostEntity> =
  postDao {
    selectById(id)
      .asFlow()
      .mapToOne(context = Dispatchers.IO)
  }

public fun DatabaseScope.insertPosts(
  posts: List<PostEntity>,
) {
  postDao {
    transaction {

      posts.forEach { insertPost(it) }
    }
  }
}
