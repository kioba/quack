package dev.kioba.design.system.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun CommentButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
) {
  IconButton(
    modifier = modifier,
    onClick = onClick,
  ) {
    Icon(
      imageVector = Icons.Outlined.ChatBubbleOutline,
      contentDescription = null,
    )
  }
}
