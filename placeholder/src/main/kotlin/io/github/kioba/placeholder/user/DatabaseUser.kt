package io.github.kioba.placeholder.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class DatabaseUser(
  @PrimaryKey
  @ColumnInfo(name = "userid")
  val id: Int,
  val username: String,
  val name: String,
  val email: String,
  val phone: String,
  val website: String
) {
  companion object
}
