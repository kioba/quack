package dev.kioba.feature.feed.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.kioba.anchor.compose.RememberAnchor
import dev.kioba.android.database.buildDatabaseScope
import dev.kioba.feature.feed.data.FeedEffects
import dev.kioba.feature.feed.data.feedAnchor
import dev.kioba.platform.network.buildNetworkScope
import kotlinx.serialization.Serializable

private fun FeedEffects(context: Context): FeedEffects =
  FeedEffects(
    networkScope = buildNetworkScope(),
    databaseScope = buildDatabaseScope(context = context)
  )

public fun NavGraphBuilder.feedComposable(
) {
  composable<FeedDestination> { stack -> stack.FeedPage() }
}

@Serializable
public data object FeedDestination

@Composable
private fun NavBackStackEntry.FeedPage() {
  val context = LocalContext.current
  RememberAnchor(
    scope = { feedAnchor(FeedEffects(context)) },
  ) { state -> FeedUi(state = state) }
}
