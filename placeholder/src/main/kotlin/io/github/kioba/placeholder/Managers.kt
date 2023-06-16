package io.github.kioba.placeholder

import io.github.kioba.platform.database.DatabaseScope
import io.github.kioba.platform.network.NetworkScope
import io.github.kioba.platform.network.buildNetworkScope

public class NetworkManager(
  internal val networkScope: NetworkScope = buildNetworkScope(),
)

public class DatabaseManager internal constructor(
  internal val databaseScope: DatabaseScope,
)