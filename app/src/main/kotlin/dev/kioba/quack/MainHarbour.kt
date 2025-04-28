package dev.kioba.quack

import androidx.navigation.NavController
import dev.kioba.feature.details.model.DetailsBackIntent
import dev.kioba.feature.details.model.DetailsDestination
import dev.kioba.feature.feed.model.DetailsSelectedIntent
import dev.kioba.platform.android.compose.navigation.NavigationIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal suspend fun NavController.mainHarbour(
  intent: NavigationIntent,
): Unit =
  withContext(Dispatchers.Main) {
    when (intent) {
      is DetailsSelectedIntent -> navigate(DetailsDestination(intent.postId))
      is DetailsBackIntent -> popBackStack()
    }
  }
