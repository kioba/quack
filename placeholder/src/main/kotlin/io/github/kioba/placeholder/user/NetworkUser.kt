package io.github.kioba.placeholder.user

import io.github.kioba.placeholder.network.network_models.Address
import io.github.kioba.placeholder.network.network_models.Company

data class NetworkUser(
  val address: Address,
  val company: Company,
  val email: String,
  val id: Int,
  val name: String,
  val phone: String,
  val username: String,
  val website: String
) {
  companion object
}
