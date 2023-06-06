package io.github.kioba.placeholder.persistence.model

import androidx.room.Entity

@Entity(tableName = "addresses")
data class DatabaseAddress(
  val city: String,
  val geo: DatabaseGeo,
  val street: String,
  val suite: String,
  val zipcode: String
) {
  companion object
}
