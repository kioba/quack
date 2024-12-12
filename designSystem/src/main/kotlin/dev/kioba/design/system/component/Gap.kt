package dev.kioba.design.system.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
public fun Gap(
  size: Dp,
) {
  Spacer(modifier = Modifier.size(size))
}