package dev.kioba.feature.feed.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.kioba.anchor.compose.anchor
import dev.kioba.feature.feed.data.FeedAnchor
import dev.kioba.feature.feed.data.dismissFeedError
import dev.kioba.feature.feed.data.dismissUserError
import dev.kioba.feature.feed.model.FeedState


@Composable
internal fun ErrorUI(state: FeedState) {
  when {
    state.feedError != null ->
      ErrorDialog(
        error = state.feedError,
        onDismissRequest = anchor(FeedAnchor::dismissFeedError),
      )

    state.userError != null ->
      ErrorDialog(
        error = state.userError,
        onDismissRequest = anchor(FeedAnchor::dismissUserError)
      )
  }
}

@Composable
private fun ErrorDialog(
  error: Throwable,
  onDismissRequest: () -> Unit,
) {
  AlertDialog(
    onDismissRequest = onDismissRequest,
    confirmButton = {
      Button(onClick = onDismissRequest) {
        Text(text = "Ok")
      }
    },
    text = {
      Text(
        text = "Feed error: ${error.message}" +
          "state: ${error.printStackTrace()}",
      )
    }
  )
}
