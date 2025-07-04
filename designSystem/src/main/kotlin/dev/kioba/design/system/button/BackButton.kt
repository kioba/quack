package dev.kioba.design.system.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
public fun BackButton(
  onClick: () -> Unit,
) {
  IconButton(onClick = onClick) {
    Icon(
      imageVector = Icons.AutoMirrored.Default.ArrowBack,
      contentDescription = "navigate up"
    )
  }
}
