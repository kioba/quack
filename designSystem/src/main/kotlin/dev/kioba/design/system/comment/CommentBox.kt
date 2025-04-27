package dev.kioba.design.system.comment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.kioba.design.system.component.Gap

@Composable
public fun CommentBox(
  name: @Composable () -> Unit,
  email: @Composable () -> Unit,
  description: @Composable () -> Unit,
  modifier: Modifier = Modifier,
) {
  Row(modifier.height(IntrinsicSize.Min)) {
    VerticalDivider(
      modifier = modifier
        .padding(vertical = 16.dp),
    )
    Column() {
      name()
      Gap(2.dp)
      email()
      Gap(8.dp)
      description()
    }
  }
}