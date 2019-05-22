package io.github.kioba.placeholder.persistence.persistence_models

import androidx.room.Entity

@Entity(tableName = "geolocations")
data class DatabaseGeo(
  val lat: String,
  val lng: String
) {
  companion object
}
