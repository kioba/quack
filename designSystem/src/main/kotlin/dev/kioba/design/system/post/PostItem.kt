package dev.kioba.design.system.post

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.kioba.design.system.component.Gap

@Composable
public fun PostItem(
  header: @Composable () -> Unit,
  body: @Composable () -> Unit,
  actions: @Composable () -> Unit,
  modifier: Modifier = Modifier,
) {
  Column(modifier) {
    header()
    Gap(8.dp)
    body()
    Gap(2.dp)
    actions()
  }
}