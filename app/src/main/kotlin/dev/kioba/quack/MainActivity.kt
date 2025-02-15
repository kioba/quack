package dev.kioba.quack

import android.graphics.Color
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.kioba.feature.feed.ui.FeedDestination
import dev.kioba.feature.feed.ui.feedComposable

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
      ) {
        feedComposable()
      }
    }
  }
}
