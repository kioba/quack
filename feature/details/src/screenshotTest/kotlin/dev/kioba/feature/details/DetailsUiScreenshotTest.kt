package dev.kioba.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.feature.details.ui.DetailsPreview
import dev.kioba.feature.details.ui.DetailsUi
import dev.kioba.platform.android.compose.SnapshotPreview

@Suppress("unused")
internal class DetailsUiScreenshotTest {

  @SnapshotPreview
  @Composable
  fun DetailsScreenshotTest(
    @PreviewParameter(DetailsPreview::class) state: DetailsViewState,
  ) {
    DetailsUi(state)
  }
}