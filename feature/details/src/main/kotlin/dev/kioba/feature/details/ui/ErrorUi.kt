package dev.kioba.feature.details.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.kioba.anchor.compose.anchor
import dev.kioba.feature.details.data.DetailsAnchor
import dev.kioba.feature.details.data.dismissError
import dev.kioba.feature.details.model.DetailsViewState


@Composable
internal fun ErrorUI(
  state: DetailsViewState,
) {
  state.error?.let { throwable ->
    ErrorDialog(
      error = throwable,
      onDismissRequest = anchor(DetailsAnchor::dismissError),
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
