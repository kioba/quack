package dev.kioba.design.system.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.kioba.design.system.component.Gap

@Composable
public fun PostItemBox(
  avatar: @Composable () -> Unit,
  title: @Composable () -> Unit,
  description: @Composable () -> Unit,
  actions: @Composable () -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(modifier = modifier) {
    avatar()
    Column {
      Column(modifier = Modifier.padding(start = 6.dp)) {
        title()
        Gap(8.dp)
        description()
        Gap(2.dp)
      }
      actions()
    }
  }
}