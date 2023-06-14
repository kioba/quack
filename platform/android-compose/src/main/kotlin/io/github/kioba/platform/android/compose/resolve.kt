package io.github.kioba.platform.android.compose

import androidx.annotation.IdRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

@ReadOnlyComposable
@Composable
public fun @receiver:IdRes Int.resolve(): String =
  LocalContext.current.getString(this)