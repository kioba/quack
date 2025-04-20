package dev.kioba.quack

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.scaleOut
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.kioba.feature.details.model.DetailsBackIntent
import dev.kioba.feature.details.model.DetailsDestination
import dev.kioba.feature.details.ui.detailsComposable
import dev.kioba.feature.feed.model.DetailsSelectedIntent
import dev.kioba.feature.feed.model.FeedDestination
import dev.kioba.feature.feed.ui.feedPage
import dev.kioba.platform.android.compose.navigation.NavigationIntent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge(
      navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
    )
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      NavHost(
        navController = navController,
        startDestination = FeedDestination,
        popExitTransition = {
          scaleOut(
            targetScale = 0.9f,
            transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 0.5f)
          )
        },
        popEnterTransition = {
          EnterTransition.None
        },
      ) {
        feedPage(mainHarbour(navController))
        detailsComposable(mainHarbour(navController))
      }
    }
  }

  private fun mainHarbour(
    navController: NavController,
  ): suspend (NavigationIntent) -> Unit =
    {
      withContext(Dispatchers.Main) {
        when (it) {
          is DetailsSelectedIntent ->
            navController.navigate(DetailsDestination(it.postId))

          is DetailsBackIntent -> navController.popBackStack()
        }
      }
    }
}
