package io.github.kioba.design.system.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TipsAndUpdates
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
public fun AboutButton(
  contentDescription: String?,
) {
  IconButton(onClick = { /*TODO*/ }) {
    Icon(
      imageVector = Icons.Outlined.TipsAndUpdates,
      contentDescription = contentDescription
    )
  }
}