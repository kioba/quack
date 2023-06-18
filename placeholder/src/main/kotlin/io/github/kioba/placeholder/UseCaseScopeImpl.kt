package io.github.kioba.placeholder

import io.github.kioba.platform.domain.NetworkManager

class UseCaseScopeImpl(
  val network: NetworkManager = NetworkManager(),
)