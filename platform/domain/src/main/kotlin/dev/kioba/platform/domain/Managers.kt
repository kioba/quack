package dev.kioba.platform.domain

import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.network.NetworkScope
import dev.kioba.platform.network.buildNetworkScope

public class NetworkManager(
  @PublishedApi
  internal val networkScope: NetworkScope = buildNetworkScope(),
)

public class DatabaseManager internal constructor(
  @PublishedApi
  internal val databaseScope: DatabaseScope,
)