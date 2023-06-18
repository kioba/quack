package io.github.kioba.platform.domain

import io.github.kioba.platform.database.DatabaseScope
import io.github.kioba.platform.network.NetworkScope
import io.github.kioba.platform.network.buildNetworkScope

public class NetworkManager(
  @PublishedApi
  internal val networkScope: NetworkScope = buildNetworkScope(),
)

public class DatabaseManager internal constructor(
  @PublishedApi
  internal val databaseScope: DatabaseScope,
)