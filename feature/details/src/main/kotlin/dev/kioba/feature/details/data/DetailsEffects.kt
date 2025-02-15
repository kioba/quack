package dev.kioba.feature.details.data

import dev.kioba.anchor.Anchor
import dev.kioba.anchor.Effect
import dev.kioba.anchor.RememberAnchorScope
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.platform.database.DatabaseScope
import dev.kioba.platform.domain.EffectContext
import dev.kioba.platform.domain.buildEffectContext
import dev.kioba.platform.network.NetworkScope
import kotlinx.coroutines.flow.Flow


internal typealias DetailsAnchor = Anchor<DetailsEffects, DetailsViewState>

public class DetailsEffects(
  databaseScope: DatabaseScope,
  networkScope: NetworkScope,
) : EffectContext by buildEffectContext(databaseScope, networkScope), Effect

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

fun onCreated(any: Any): Flow<*> {
  TODO("Not yet implemented")
}
