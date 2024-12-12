package dev.kioba.platform.android.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Effect
import dev.kioba.anchor.ViewState
import dev.kioba.anchor.compose.RememberAnchor

public inline fun <reified C : Anchor<E, S>, reified S : ViewState, E : Effect> Fragment.renderWith(
  noinline scope: () -> C,
  noinline content: @Composable C.(S) -> Unit,
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