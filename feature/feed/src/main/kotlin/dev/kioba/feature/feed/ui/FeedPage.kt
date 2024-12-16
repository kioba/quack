package dev.kioba.feature.feed.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import dev.kioba.anchor.compose.RememberAnchor
import dev.kioba.android.database.buildDatabaseScope
import dev.kioba.feature.feed.data.FeedEffects
import dev.kioba.feature.feed.data.feedAnchor

@Composable
public fun ComponentActivity.FeedPage() {
    RememberAnchor(
        scope = { feedAnchor(FeedEffects(buildDatabaseScope(this))) },
    ) { state -> FeedUi(state = state) }
}
