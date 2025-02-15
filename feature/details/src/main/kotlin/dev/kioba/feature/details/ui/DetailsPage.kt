package dev.kioba.feature.details.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.kioba.anchor.compose.RememberAnchor
import dev.kioba.android.database.buildDatabaseScope
import dev.kioba.feature.details.data.DetailsEffects
import dev.kioba.feature.details.data.detailsAnchor
import dev.kioba.platform.network.buildNetworkScope
import kotlinx.serialization.Serializable


private fun DetailsEffects(
  context: Context,
): DetailsEffects =
  DetailsEffects(
    networkScope = buildNetworkScope(),
    databaseScope = buildDatabaseScope(context),
  )

public fun NavGraphBuilder.feedComposable(
) {
  composable<DetailsDestination> { stack -> stack.DetailsPage() }
}

@Serializable
public data object DetailsDestination

@Composable
private fun NavBackStackEntry.DetailsPage() {
  val context = LocalContext.current
  RememberAnchor(
    scope = { detailsAnchor(DetailsEffects(context)) },
  ) { state -> DetailsUi(state = state) }
}
