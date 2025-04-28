package dev.kioba.feature.feed.ui

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.kioba.anchor.compose.RememberAnchor
import dev.kioba.android.database.buildDatabaseScope
import dev.kioba.feature.feed.data.FeedEffects
import dev.kioba.feature.feed.data.feedAnchor
import dev.kioba.feature.feed.model.FeedDestination
import dev.kioba.platform.android.compose.navigation.NavigationIntent
import dev.kioba.platform.network.buildNetworkScope

private fun effectBuilder(
  context: Context,
  navFlow: suspend (NavigationIntent) -> Unit,
): FeedEffects =
  FeedEffects(
    networkScope = buildNetworkScope(),
    databaseScope = buildDatabaseScope(context),
    navFlow = navFlow,
  )

@OptIn(ExperimentalMaterial3Api::class)
public fun NavGraphBuilder.feedPage(
  navFlow: suspend (NavigationIntent) -> Unit
) {
  composable<FeedDestination> { stack ->
    val context = LocalContext.current
    stack.RememberAnchor(
      scope = { feedAnchor(effectBuilder(context, navFlow)) },
    ) { state -> FeedUi(state) }
  }
}