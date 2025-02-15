package dev.kioba.feature.feed.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Insights
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.kioba.anchor.compose.anchor
import dev.kioba.design.system.button.AboutButton
import dev.kioba.design.system.button.ProfileButton
import dev.kioba.design.system.component.Avatar
import dev.kioba.design.system.component.AvatarSize
import dev.kioba.design.system.component.Gap
import dev.kioba.design.system.component.avatarSize
import dev.kioba.design.system.post.PostItem
import dev.kioba.design.system.theme.appBarTitle
import dev.kioba.feature.feed.data.FeedAnchor
import dev.kioba.feature.feed.data.dismissFeedError
import dev.kioba.feature.feed.data.dismissUserError
import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.feature.feed.model.StringsR

@ExperimentalMaterial3Api
@Composable
@Preview(name = "Light Mode")
@Preview(
  name = "Dark Mode",
  showSystemUi = true,
  apiLevel = 34,
  uiMode = Configuration.UI_MODE_NIGHT_YES,
)
internal fun FeedUi(
  @PreviewParameter(FeedPreview::class) state: FeedState,
) {
  MaterialTheme {
    Box {
      val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

      Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { FeedAppBar(state, scrollBehavior) },
        contentWindowInsets = WindowInsets.statusBars,
      ) { paddingValues ->
        FeedContent(
          state = state,
          modifier = Modifier.padding(paddingValues)
    )
      }

      AnimatedVisibility(
        visible = state.feedLoading || state.usersLoading,
        modifier = Modifier.align(Alignment.Center),
      ) {
        CircularProgressIndicator()
      }when {
      state.feedError != null ->
        AlertDialog(
          onDismissRequest = anchor(FeedAnchor::dismissFeedError),
          confirmButton = {
            Button(onClick = anchor(FeedAnchor::dismissFeedError)) {
              Text(text = "Ok")
            }
          },
          text = {
            Text(
              text = "Feed error: ${state.feedError.message}" +
                "state: ${state.feedError.printStackTrace()}",
            )
          }
        )

      state.userError != null ->
        AlertDialog(
          onDismissRequest = anchor(FeedAnchor::dismissUserError),
          confirmButton = {
            Button(onClick = anchor(FeedAnchor::dismissUserError)) {
              Text(text = "Ok")
            }
          },
          text = {
            Text(
              text = "Feed error: ${state.userError.message}" +
                "state: ${state.userError.printStackTrace()}",
            )
          }
        )
    }
  }
}


@Composable
private fun FeedContent(
  state: FeedState,
  modifier: Modifier = Modifier,
) {
  LazyColumn(
    contentPadding = PaddingValues(
      top = 16.dp,
      bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
    ),
    modifier = modifier,
  ) {
    itemsIndexed(
      items = state.combined,
      key = { _, item -> item.post.id.value },
      contentType = { _, _ -> CombinedFeedItem::class },
    ) { index, item ->
      if (index != 0) {
        Gap(16.dp)
      }

      PostItem(
        header = { PostHeader(item) },
        body = { PostContent(item) },
        actions = { PostActionBar() },
      )
    }
  }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FeedAppBar(
  state: FeedState,
  scrollBehavior: TopAppBarScrollBehavior,
) {
  TopAppBar(
    navigationIcon = { ProfileButton(url = state.user.avatar.value) {} },
    title = { AppBarTitle() },
    actions = { FeedAboutButton() },
  scrollBehavior = scrollBehavior
  )
}

@Composable
private fun FeedAboutButton() {
  AboutButton(contentDescription = stringResource(StringsR.about_the_app_creator))
}

@Composable
private fun AppBarTitle() {
  Text(
    style = MaterialTheme.typography.appBarTitle,
    text = stringResource(StringsR.app_name),
  )
}

@Composable
private fun PostContent(item: CombinedFeedItem) {
  Column(
    modifier = Modifier
      .padding(
        start = 16.dp + avatarSize(AvatarSize.Medium) + 6.dp,
        end = 16.dp,
      ),
  ) {
    Text(
      style = MaterialTheme.typography.titleMedium,
      text = item.post.title,
    )
    Gap(8.dp)
    Text(
      modifier = Modifier.padding(end = 16.dp),
      style = MaterialTheme.typography.bodyMedium,
      text = item.post.body,
      maxLines = 5,
      overflow = TextOverflow.Ellipsis
    )
  }
}

@Composable
private fun PostActionBar() {
  Row(
    modifier = Modifier.padding(start = 4.dp, end = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    IconButton(onClick = { /*TODO*/ }) {
      Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = null)
    }
    IconButton(onClick = { /*TODO*/ }) {
      Icon(imageVector = Icons.Outlined.Cached, contentDescription = null)
    }
    IconButton(onClick = { /*TODO*/ }) {
      Icon(imageVector = Icons.Outlined.ChatBubbleOutline, contentDescription = null)
    }
    Spacer(modifier = Modifier.weight(1f))
    Icon(
      modifier = Modifier.size(24.dp),
      imageVector = Icons.Outlined.Insights,
      contentDescription = null,
    )
  }
}

@Composable
private fun PostHeader(item: CombinedFeedItem) {
  Row(
    modifier = Modifier.padding(horizontal = 16.dp),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    item.avatar
      ?.let { avatarUrl -> Avatar(avatarUrl) }
    Gap(6.dp)
    item.user
      ?.let {
        Text(
          style = MaterialTheme.typography.titleMedium,
          text = it.name.value,
        )
      }
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
