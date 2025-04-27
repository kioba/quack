package dev.kioba.persistence.post

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import dev.kioba.persistence.comment.CommentEntity
import dev.kioba.platform.database.DatabaseScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

//
public fun DatabaseScope.readComments(): List<CommentEntity> =
  commentDao { selectAll().executeAsList() }

public fun DatabaseScope.readCommentsByPost(
  postId: Long,
): List<CommentEntity> =
  commentDao { selectByPostId(postId).executeAsList() }

public fun DatabaseScope.streamComments(): Flow<List<CommentEntity>> =
  commentDao {
    selectAll()
      .asFlow()
      .mapToList(context = Dispatchers.IO)
  }

public fun DatabaseScope.streamComment(
  id: Long,
): Flow<CommentEntity> =
  commentDao {
    selectById(id)
      .asFlow()
      .mapToOne(context = Dispatchers.IO)
  }

public fun DatabaseScope.streamCommentsByPost(
  postId: Long,
): Flow<List<CommentEntity>> =
  commentDao {
    selectByPostId(postId)
      .asFlow()
      .mapToList(context = Dispatchers.IO)
  }

public fun DatabaseScope.insertComments(
  posts: List<CommentEntity>,
) {
  commentDao {
    transaction {
      posts.forEach { insertPost(it) }
    }
  }
}
