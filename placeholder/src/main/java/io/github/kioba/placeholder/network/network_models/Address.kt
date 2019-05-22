package io.github.kioba.placeholder.network.network_models

data class Address(
  val city: String,
  val geo: Geo,
  val street: String,
  val suite: String,
  val zipcode: String
) {
  companion object
}
