package dev.kioba.persistence.post

import dev.kioba.persistence.PostDB
import dev.kioba.persistence.comment.CommentEntityQueries
import dev.kioba.platform.database.DatabaseScope

internal object DB {
  var db: PostDB? = null
}

internal fun <R> DatabaseScope.postDao(
  f: PostEntityQueries.() -> R,
): R =
  when (DB.db) {
    null -> createDatabase(PostDB.Schema, PostDB::invoke)
      .also { DB.db = it }

    else -> DB.db!!
  }.postEntityQueries.f()

internal fun <R> DatabaseScope.commentDao(
  f: CommentEntityQueries.() -> R,
): R =
  when (DB.db) {
    null -> createDatabase(PostDB.Schema, PostDB::invoke)
      .also { DB.db = it }

    else -> DB.db!!
  }.commentEntityQueries.f()
