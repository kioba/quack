package dev.kioba.platform.android.compose

import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

@Retention(AnnotationRetention.BINARY)
@Target(
  AnnotationTarget.ANNOTATION_CLASS,
  AnnotationTarget.FUNCTION
)
@PreviewScreenSizes
@PreviewFontScale
@PreviewLightDark
@PreviewDynamicColors
public annotation class SnapshotPreview