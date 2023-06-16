package io.github.kioba.platform.android.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dev.kioba.anchor.AnchorScope
import dev.kioba.anchor.compose.RememberAnchorScope

public inline fun <reified C : AnchorScope<S, *>, S> Fragment.renderWith(
  noinline scope: () -> C,
  crossinline content: @Composable (S) -> Unit,
): ComposeView =
  ComposeView(requireContext())
    .apply {
      setContent {
        RememberAnchorScope(scope, content)
      }
    }