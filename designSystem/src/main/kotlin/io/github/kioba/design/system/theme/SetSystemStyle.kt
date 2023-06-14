package io.github.kioba.design.system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
public fun SetSystemStyle(
  color: Color,
) {
  val systemUiController = rememberSystemUiController()
  val useDarkIcons = !isSystemInDarkTheme()

  DisposableEffect(systemUiController, useDarkIcons) {
    systemUiController.setSystemBarsColor(
      color = color,
      darkIcons = useDarkIcons
    )

    systemUiController.setStatusBarColor(
      color = color,
      darkIcons = useDarkIcons,
    )

    systemUiController.setNavigationBarColor(
      color = color,
      darkIcons = useDarkIcons,
    )

    onDispose {}
  }
}