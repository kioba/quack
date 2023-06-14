package io.github.kioba.design.system.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


public val Typography.appBarTitle: TextStyle
  get() = titleLarge.copy(fontWeight = FontWeight.W900)