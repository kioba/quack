package io.github.kioba.placeholder.persistence.model

import androidx.room.Entity

@Entity(tableName = "companies")
data class DatabaseCompany(
  val bs: String,
  val catchPhrase: String,
  val name: String
) {
  companion object
}
