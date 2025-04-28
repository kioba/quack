package dev.kioba.feature.details.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import dev.kioba.design.system.comment.CommentBox
import dev.kioba.design.system.component.Avatar
import dev.kioba.design.system.post.PostContent
import dev.kioba.design.system.post.PostDetailBox
import dev.kioba.design.system.post.PostTitle
import dev.kioba.feature.details.data.DetailsAnchor
import dev.kioba.feature.details.data.navigateUp
import dev.kioba.feature.details.model.CommentViewState
import dev.kioba.feature.details.model.DetailsContentViewState
import dev.kioba.feature.details.model.DetailsViewState
import dev.kioba.feature.details.model.PostAndUserViewState


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
        DetailsContent(
          state = state.content,
          contentPadding = paddingValues,
        )
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
    visible = state.isPostLoading,
    modifier = Modifier.align(Alignment.Center),
  ) {
    CircularProgressIndicator(
      modifier = Modifier
        .align(Alignment.Center),
    )
  }
}

@Composable
internal fun DetailsContent(
  state: DetailsContentViewState,
  contentPadding: PaddingValues,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    modifier = modifier,
    contentPadding = contentPadding,
  ) {
    item(
      key = "post",
    ) {
      when (state.postAndUser) {
        null -> Unit
        else -> PostDetails(state.postAndUser)
      }
    }
    state.comments?.let { comments ->
      item { HorizontalDivider() }
      items(
        items = comments,
        key = { comment -> "comment_${comment.id}" },
      ) { comment -> CommentDetails(comment) }
    }
  }
}

@Composable
private fun PostDetails(
  state: PostAndUserViewState,
  modifier: Modifier = Modifier,
) {
  PostDetailBox(
    avatar = { Avatar(state.user.avatar) },
    title = { PostTitle(state.user.name) },
    description = { PostContent(state.post.title, state.post.body) },
    actions = { PostActionBar() },
    modifier = modifier
      .padding(top = 8.dp, bottom = 8.dp)
      .padding(horizontal = 16.dp),
  )
}

@Composable
private fun CommentDetails(
  comment: CommentViewState,
  modifier: Modifier = Modifier,
) {
  CommentBox(
    name = {
      Text(
        style = MaterialTheme.typography.titleMedium,
        text = comment.user.name,
      )
    },
    email = {
      Text(
        style = MaterialTheme.typography.bodySmall,
        text = comment.user.email,
      )
    },
    description = {
      Text(
        modifier = Modifier.fillMaxWidth(),
        style = MaterialTheme.typography.bodyMedium,
        text = comment.body,
      )
    },
    modifier = modifier
      .padding(start = 8.dp, end = 16.dp)
      .padding(vertical = 8.dp),
  )
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