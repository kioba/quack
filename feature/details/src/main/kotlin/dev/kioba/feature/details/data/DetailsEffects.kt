package dev.kioba.feature.details.data

import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Created
import dev.kioba.anchor.Effect
import dev.kioba.anchor.RememberAnchorScope
import dev.kioba.anchor.SubscriptionsScope
import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.post.api.listenCommentsByPost
import dev.kioba.domain.post.api.listenPost
import dev.kioba.domain.post.api.model.Comment
import dev.kioba.domain.post.api.model.Post
import dev.kioba.domain.post.api.syncComments
import dev.kioba.domain.user.api.listenUser
import dev.kioba.feature.details.model.DetailsBackIntent
import dev.kioba.feature.details.model.DetailsDestination
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.platform.android.compose.navigation.NavigationContext
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
import kotlinx.coroutines.flow.map


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
    listen(::onCreatedFetchComments)
  }

@OptIn(ExperimentalCoroutinesApi::class)
internal fun DetailsSubscription.onCreated(
  event: Flow<Created>,
): Flow<*> =
  with(effect) {
    event.flatMapLatest { listenPost(destination.postId) }
      .flatMapLatest { post -> listenUser(post.userId).map { user -> post to user } }
      .anchor(DetailsAnchor::onUpdateDetails)
  }

internal suspend fun DetailsAnchor.init() {
  effect { syncComments(destination.postId) }
}

internal suspend fun DetailsAnchor.navigateUp() {
  navigate { DetailsBackIntent }
}

internal fun DetailsAnchor.dismissError() {
  reduce { hideError() }
}

internal fun DetailsAnchor.onUpdateDetails(
  initData: Pair<Post, User>,
) {
  val (post, user) = initData
  reduce { updatePostAndUser(post, user) }
}

@OptIn(ExperimentalCoroutinesApi::class)
internal fun DetailsSubscription.onCreatedFetchComments(
  event: Flow<Created>,
): Flow<*> =
  with(effect) {
    event.flatMapLatest { listenCommentsByPost(destination.postId) }
      .anchor(DetailsAnchor::onUpdateComments)
  }

internal fun DetailsAnchor.onUpdateComments(
  comments: List<Comment>,
) {
  reduce { updateComments(comments) }
}
