package dev.kioba.design.system.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun RepostButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  IconButton(
    modifier = modifier,
    onClick = onClick,
  ) {
    Icon(
      imageVector = Icons.Outlined.Cached,
      contentDescription = null,
    )
  }
}
