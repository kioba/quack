package dev.kioba.feature.details.ui

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.kioba.anchor.compose.RememberAnchor
import dev.kioba.android.database.buildDatabaseScope
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.feature.details.data.DetailsEffects
import dev.kioba.feature.details.data.detailsAnchor
import dev.kioba.feature.details.model.DetailsDestination
import dev.kioba.feature.details.model.PostIdParameterType
import dev.kioba.platform.android.compose.navigation.NavigationIntent
import dev.kioba.platform.network.buildNetworkScope
import kotlin.reflect.typeOf

private fun effectBuilder(
  context: Context,
  destination: DetailsDestination,
  navFlow: suspend (NavigationIntent) -> Unit,
): DetailsEffects =
  DetailsEffects(
    databaseScope = buildDatabaseScope(context),
    networkScope = buildNetworkScope(),
    destination = destination,
    navFlow = navFlow,
  )

public fun NavGraphBuilder.detailsPage(
  navFlow: suspend (NavigationIntent) -> Unit,
) {
  composable<DetailsDestination>(
    typeMap = mapOf(typeOf<PostId>() to PostIdParameterType)
  ) { stack ->
    val context = LocalContext.current
    stack.RememberAnchor(
      scope = { detailsAnchor(effectBuilder(context, stack.toRoute(), navFlow)) },
    ) { state -> DetailsUi(state = state) }
  }
}
