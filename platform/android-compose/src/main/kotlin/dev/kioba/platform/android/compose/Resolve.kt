package dev.kioba.platform.android.compose

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.stringResource

@ReadOnlyComposable
@Composable
public fun @receiver:StringRes Int.resolve(): String = stringResource(this)
