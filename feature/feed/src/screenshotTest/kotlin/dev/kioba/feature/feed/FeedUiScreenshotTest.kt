package dev.kioba.feature.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.feature.feed.ui.FeedPreview
import dev.kioba.feature.feed.ui.FeedUi
import dev.kioba.platform.android.compose.SnapshotPreview

@Suppress("unused")
internal class FeedUiScreenshotTest {


  @SnapshotPreview
  @Composable
  fun FeedScreenshotTest(
    @PreviewParameter(FeedPreview::class) state: FeedState,
  ) {
    FeedUi(state)
  }
}