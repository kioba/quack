package io.github.kioba.design.system.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
public fun Avatar(
  url: String,
  modifier: Modifier = Modifier,
  contentDescription: String? = null,
  placeholder: Painter = rememberVectorPainter(Icons.Default.Person),
  size: AvatarSize = AvatarSize.Medium,
) {
  AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
      .data(url)
      .crossfade(true)
      .build(),
    placeholder = placeholder,
    error = placeholder,
    contentDescription = contentDescription,
    modifier = modifier
      .size(getSize(size))
      .clip(CircleShape)
  )
}

public enum class AvatarSize {
  Large,
  Medium;
}

private fun getSize(
  size: AvatarSize,
): Dp =
  when (size) {
    AvatarSize.Large -> 48.dp
    AvatarSize.Medium -> 36.dp
  }