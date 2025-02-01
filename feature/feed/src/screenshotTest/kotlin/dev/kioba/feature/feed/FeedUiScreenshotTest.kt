package dev.kioba.feature.feed

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.feature.feed.ui.FeedPreview
import dev.kioba.feature.feed.ui.FeedUi

@Suppress("unused")
internal class FeedUiScreenshotTest {
//
//  @Preview(
//    group = "portrait",
//    showBackground = true,
//    device = "spec:width=1080px,height=2424px,cutout=double",
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
//  )
//  @Preview(
//    group = "portrait",
//    showBackground = true,
//    device = "spec:width=1080px,height=2424px,cutout=double",
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
//  )
//  @Preview(
//    group = "landscape",
//    showBackground = true,
//    device = "spec:width=1080px,height=2424px,cutout=double,orientation=landscape",
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
//  )
//  @Preview(
//    group = "landscape",
//    showBackground = true,
//    device = "spec:width=1080px,height=2424px,cutout=double,orientation=landscape",
//    showSystemUi = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
//  )
  @PreviewScreenSizes
  @PreviewFontScale
  @PreviewLightDark
  @PreviewDynamicColors
  @Composable
  fun FeedScreenshotTest(
    @PreviewParameter(FeedPreview::class) state: FeedState,
  ) {
    FeedUi(state)
  }
}