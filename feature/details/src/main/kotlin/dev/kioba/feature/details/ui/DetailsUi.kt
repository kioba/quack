package dev.kioba.feature.details.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import dev.kioba.anchor.compose.anchor
import dev.kioba.design.system.button.BackButton
import dev.kioba.design.system.button.CommentButton
import dev.kioba.design.system.button.LikeButton
import dev.kioba.design.system.button.RepostButton
import dev.kioba.design.system.component.Avatar
import dev.kioba.design.system.component.Gap
import dev.kioba.design.system.post.PostContent
import dev.kioba.design.system.post.PostTitle
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
    modifier = modifier,
  ) {
    item {
      PostDetailBox(
        avatar = { Avatar(state.user.avatar) },
        title = { PostTitle(state.user.name) },
        description = { PostContent(state.post.title, state.post.body) },
        actions = { PostActionBar() },
        modifier = Modifier
          .padding(top = 8.dp, bottom = 8.dp)
          .padding(horizontal = 16.dp),
      )
    }
    item { HorizontalDivider() }
  }
}

@Composable
private fun PostDetailBox(
  avatar: @Composable () -> Unit,
  title: @Composable () -> Unit,
  description: @Composable () -> Unit,
  actions: @Composable () -> Unit,
  modifier: Modifier,
) {
  Column(
    modifier = modifier,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
    ) {
      avatar()
      Gap(6.dp)
      title()
    }
    Gap(8.dp)
    description()
    Gap(2.dp)
    actions()
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
private fun PostActionBar(
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    LikeButton { /*TODO*/ }
    RepostButton { /*TODO*/ }
    CommentButton { /*TODO*/ }
    Spacer(modifier = Modifier.weight(1f))
    Icon(
      modifier = Modifier.size(24.dp),
      imageVector = Icons.Outlined.Insights,
      contentDescription = null,
    )
  }
}