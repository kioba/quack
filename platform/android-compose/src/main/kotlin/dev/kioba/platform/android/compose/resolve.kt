package dev.kioba.platform.android.compose

import androidx.annotation.IdRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

@ReadOnlyComposable
@Composable
public fun @receiver:IdRes Int.resolve(): String =
  stringResource(this)