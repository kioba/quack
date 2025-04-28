package dev.kioba.design.system.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.kioba.design.system.component.Gap

@Composable
public fun PostDetailBox(
  avatar: @Composable () -> Unit,
  title: @Composable () -> Unit,
  description: @Composable () -> Unit,
  actions: @Composable () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      avatar()
      Gap(6.dp)
      title()
    }
    Gap(8.dp)
    description()
    Gap(2.dp)
    actions()
  }
}
