package io.github.kioba.persistence.post

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import io.github.kioba.persistence.PostDB
import io.github.kioba.persistence.PostEntity
import io.github.kioba.persistence.PostEntityQueries
import io.github.kioba.platform.database.DatabaseScope
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
      .mapToList()
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
