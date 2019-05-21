package io.github.kioba.placeholder.persistence.persistence_models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class DatabasePost(
  @PrimaryKey
  @ColumnInfo(name = "postid")
  val id: Int,
  val body: String,
  val title: String,
  val userId: Int
) {
  companion object
}
