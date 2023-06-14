package io.github.kioba.design.system.button

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import io.github.kioba.design.system.component.Avatar
import io.github.kioba.design.system.component.AvatarSize

@Composable
fun ProfileButton(
  url: String,
  onClick: () -> Unit,
) {
  IconButton(onClick = onClick) {
    Avatar(
      url = url,
      contentDescription = "profile picture",
      size = AvatarSize.Large,
    )
  }
}