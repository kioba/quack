package dev.kioba.feature.feed.ui

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import dev.kioba.anchor.compose.RememberAnchor
import dev.kioba.android.database.buildDatabaseScope
import dev.kioba.feature.feed.data.FeedEffects
import dev.kioba.feature.feed.data.feedAnchor
import dev.kioba.platform.network.buildNetworkScope

private fun FeedEffects(context: Context): FeedEffects =
  FeedEffects(
    networkScope = buildNetworkScope(),
    databaseScope = buildDatabaseScope(context)
  )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun ComponentActivity.FeedPage() {
  RememberAnchor(
    scope = { feedAnchor(FeedEffects(this@FeedPage)) },
  ) { state -> FeedUi(state = state) }
}
