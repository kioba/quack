package dev.kioba.feature.details.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import dev.kioba.anchor.compose.anchor
import dev.kioba.design.system.button.BackButton
import dev.kioba.design.system.component.Avatar
import dev.kioba.design.system.component.Gap
import dev.kioba.feature.details.data.DetailsAnchor
import dev.kioba.feature.details.data.navigateUp
import dev.kioba.feature.details.model.DetailsContentViewState
import dev.kioba.feature.details.model.DetailsViewState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsUi(
  state: DetailsViewState,
) {
  MaterialTheme {
    Box(Modifier.fillMaxSize()) {
      val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

      Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { DetailsAppBar(scrollBehavior) },
        contentWindowInsets = WindowInsets.safeDrawing,
      ) { paddingValues ->
        AnimatedContent(state.content) { target ->
          when (target) {
            null -> {}
            else -> DetailsContent(
              state = target,
              modifier = Modifier.padding(paddingValues)
            )
          }

        }
      }

      LoadingUi(state)

      ErrorUI(state)
    }
  }
}

@Composable
internal fun BoxScope.LoadingUi(
  state: DetailsViewState,
) {
  AnimatedVisibility(
    visible = state.isLoading,
    modifier = Modifier.Companion.align(Alignment.Center),
  ) {
    CircularProgressIndicator(
      modifier = Modifier.Companion
        .align(Alignment.Center),
    )
  }
}

@Composable
internal fun DetailsContent(
  state: DetailsContentViewState,
  modifier: Modifier,
) {
  LazyColumn(
    contentPadding = PaddingValues(horizontal = 16.dp),
    modifier = modifier,
  ) {
    item {

    }
    item {
      Gap(16.dp)
    }
    item {
      Text(
        text = state.title,
        style = MaterialTheme.typography.titleMedium,
      )
    }
    item { Gap(16.dp) }
    item {
      Text(
        text = state.body,
        style = MaterialTheme.typography.bodyMedium,
      )
    }
  }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailsAppBar(
  scrollBehavior: TopAppBarScrollBehavior,
) {
  TopAppBar(
    title = {},
    navigationIcon = { BackButton(anchor(DetailsAnchor::navigateUp)) },
    scrollBehavior = scrollBehavior,
  )
}


@Composable
private fun PostHeader() {
  Row(
    modifier = Modifier.padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
//    AnimatedVisibility(item.avatar != null) {
//      item.avatar?.let { avatarUrl -> Avatar(avatarUrl) }
//    }
//    Gap(6.dp)
//    item.user
//      ?.let {
//        Text(
//          style = MaterialTheme.typography.titleMedium,
//          text = it.name.value,
//        )
//      }
    Gap(4.dp)
    Text(
      style = MaterialTheme.typography.labelSmall,
      text = "•",
    )
    Gap(4.dp)
    Text(
      style = MaterialTheme.typography.labelSmall,
      text = "12m",
    )
  }
}