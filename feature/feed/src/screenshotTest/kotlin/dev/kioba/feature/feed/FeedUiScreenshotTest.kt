package dev.kioba.feature.feed

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.feature.feed.ui.FeedPreview
import dev.kioba.feature.feed.ui.FeedUi

@Suppress("unused")
internal class FeedUiScreenshotTest {

  @Preview(
    showBackground = true,
  )
  @Composable
  fun FeedScreenshotTest(
    @PreviewParameter(FeedPreview::class) state: FeedState,
  ) {
    FeedUi(state)
  }
}