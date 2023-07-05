package io.github.kioba.platform.android.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dev.kioba.anchor.AnchorScope
import dev.kioba.anchor.compose.RememberAnchor

public inline fun <reified C : AnchorScope<S, E>, reified S, E> Fragment.renderWith(
  noinline scope: () -> C,
  noinline content: @Composable (S) -> Unit,
): ComposeView =
  ComposeView(requireContext())
    .apply {
      setContent {
        RememberAnchor(
          scope = scope,
          content = content,
        )
      }
    }