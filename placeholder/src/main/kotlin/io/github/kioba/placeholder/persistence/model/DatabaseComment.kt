package io.github.kioba.placeholder.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class DatabaseComment(
  @PrimaryKey
  @ColumnInfo(name = "commentid")
  val id: Int,
  val body: String,
  val email: String,
  val name: String,
  val postId: Int
)
