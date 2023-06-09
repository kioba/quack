package io.github.kioba.feed.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.kioba.feed.model.FeedState

@Composable
fun FeedUi(
  state: FeedState,
) {
  MaterialTheme {
    Scaffold { paddingValues ->
      LazyColumn(
        modifier = Modifier.padding(paddingValues)
      ) {
        items(state.combined) {
          Column {
            Text(text = it.post.title)
            Text(text = it.post.body)
//            it.avatar
//              .orNull()
//              ?.let { avatar -> Text(text = avatar) }
//
            it.user
              .orNull()
              ?.let { user ->
                Text(text = user.name)
                Text(text = user.email)
                Text(text = user.avatar)
              }
          }
        }
      }
    }
  }
}