package dev.kioba.design.system.post

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import dev.kioba.design.system.component.Gap

@Composable
public fun PostTitle(
  userName: String,
) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Text(
      style = MaterialTheme.typography.bodyLarge,
      text = userName,
    )
    Gap(4.dp)
    Text(
      style = MaterialTheme.typography.labelSmall,
      text = "•",
    )
    Gap(4.dp)
    Text(
      style = MaterialTheme.typography.labelSmall,
      text = "12m",
    )
  }
}