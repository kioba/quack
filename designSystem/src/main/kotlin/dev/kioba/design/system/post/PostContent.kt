package dev.kioba.design.system.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.kioba.design.system.component.Gap

@Composable
public fun PostContent(
  title: String,
  content: String,
  modifier: Modifier = Modifier,
  maxLines: Int = Int.MAX_VALUE,
) {
  Column(modifier = modifier) {
    Text(
      style = MaterialTheme.typography.titleMedium,
      text = title,
    )
    Gap(8.dp)
    Text(
      modifier = Modifier.fillMaxWidth(),
      style = MaterialTheme.typography.bodyMedium,
      text = content,
      maxLines = 5,
      overflow = TextOverflow.Ellipsis
    )
  }
}