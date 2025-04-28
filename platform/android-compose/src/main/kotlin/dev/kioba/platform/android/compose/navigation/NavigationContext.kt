package dev.kioba.platform.android.compose.navigation

import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Effect


public fun buildNavigationContext(
  navFlow: suspend (NavigationIntent) -> Unit,
): NavigationContext =
  object : NavigationContext {
    override val navigationFlow: suspend (NavigationIntent) -> Unit = navFlow
  }

public interface NavigationDestination

public suspend inline fun <N, T> Anchor<N, *>.navigate(
  crossinline block: () -> T,
) where T : NavigationIntent, N : NavigationContext, N : Effect {
  effect { navigationFlow(block()) }
}

public interface NavigationContext {
  public val navigationFlow: suspend (NavigationIntent) -> Unit
}
