package dev.kioba.quack

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.kioba.feature.details.ui.detailsPage
import dev.kioba.feature.feed.model.FeedDestination
import dev.kioba.feature.feed.ui.feedPage

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
        popEnterTransition = {
          EnterTransition.None
        },
        popExitTransition = {
          slideOutOfContainer(
            animationSpec = spring(
              visibilityThreshold = IntOffset.VisibilityThreshold
            ),
            towards = AnimatedContentTransitionScope.SlideDirection.End,
            targetOffset = { offsetForFullSlide -> (offsetForFullSlide * 2f / 6f).toInt() },
          ).plus(fadeOut(animationSpec = tween(300, delayMillis = 90)))

        },
        enterTransition = {
          slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
          ).plus(fadeIn(animationSpec = tween(300, delayMillis = 90)))
        },
      ) {
        feedPage(navController::mainHarbour)
        detailsPage(navController::mainHarbour)
      }
    }
  }
}
