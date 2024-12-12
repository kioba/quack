package dev.kioba.persistence.post

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import dev.kioba.persistence.PostEntity
import dev.kioba.persistence.PostEntityQueries
import dev.kioba.platform.database.DatabaseScope
import io.github.kioba.persistence.PostDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

private object DB {
  var query: PostEntityQueries? = null
}

private fun <R> DatabaseScope.postDao(
  f: PostEntityQueries.() -> R,
): R =
  if (DB.query == null) {
    DB.query = createDatabase(PostDB.Schema, PostDB::invoke)
      .postEntityQueries
    DB.query!!
  } else {
    DB.query!!
  }.f()


public fun DatabaseScope.readPosts(): List<PostEntity> =
  postDao { selectAll().executeAsList() }

public fun DatabaseScope.streamPosts(): Flow<List<PostEntity>> =
  postDao {
    selectAll()
      .asFlow()
      .mapToList(context = Dispatchers.IO)
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
