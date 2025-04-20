package dev.kioba.feature.details.data

import androidx.navigation.NavHostController
import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Created
import dev.kioba.anchor.Effect
import dev.kioba.anchor.RememberAnchorScope
import dev.kioba.anchor.SubscriptionsScope
import dev.kioba.domain.post.api.listenPost
import dev.kioba.domain.post.api.model.Post
import dev.kioba.feature.details.model.DetailsBackIntent
import dev.kioba.feature.details.model.DetailsDestination
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.platform.android.compose.navigation.NavigationContext
import dev.kioba.platform.android.compose.navigation.NavigationDestination
import dev.kioba.platform.android.compose.navigation.NavigationIntent
import dev.kioba.platform.android.compose.navigation.buildNavigationContext
import dev.kioba.platform.android.compose.navigation.navigate
import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.buildEffectContext
import dev.kioba.platform.network.NetworkScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest


internal typealias DetailsAnchor = Anchor<DetailsEffects, DetailsViewState>
internal typealias DetailsSubscription = SubscriptionsScope<DetailsEffects, DetailsViewState>

public class DetailsEffects(
  databaseScope: DatabaseScope,
  networkScope: NetworkScope,
  public val destination: DetailsDestination,
  navFlow: suspend (NavigationIntent) -> Unit,
) :
  EffectContext by buildEffectContext(databaseScope, networkScope),
  NavigationContext by buildNavigationContext(navFlow),
  Effect

internal fun RememberAnchorScope.detailsAnchor(
  effectsScope: DetailsEffects,
): DetailsAnchor =
  create(
    initialState = ::DetailsViewState,
    effectScope = { effectsScope },
    init = DetailsAnchor::init,
  ) {
    listen(::onCreated)
  }

@OptIn(ExperimentalCoroutinesApi::class)
internal fun DetailsSubscription.onCreated(
  event: Flow<Created>,
): Flow<*> =
  with(effect) {
    event.flatMapLatest { listenPost(destination.postId) }
      .anchor(DetailsAnchor::onPost)
  }

internal fun DetailsAnchor.init() {}

internal suspend fun DetailsAnchor.navigateUp() {
  navigate { DetailsBackIntent }
}

internal fun DetailsAnchor.dismissError() {
  reduce { hideError() }
}

internal fun DetailsAnchor.onPost(
  post: Post,
) {
  reduce {
    withPost(post)
      .hideLoading()
  }
}