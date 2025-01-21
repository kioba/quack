package dev.kioba.platform.android.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Effect
import dev.kioba.anchor.RememberAnchorScope
import dev.kioba.anchor.ViewState
import dev.kioba.anchor.compose.RememberAnchor

public inline fun <reified S : ViewState, E : Effect> Fragment.renderWith(
  noinline scope: @DisallowComposableCalls RememberAnchorScope.() -> Anchor<E, S>,
  crossinline content: @Composable Anchor<E, S>.(S) -> Unit,
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