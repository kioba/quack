package dev.kioba.feature.feed.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.kioba.anchor.compose.anchor
import dev.kioba.design.system.button.AboutButton
import dev.kioba.design.system.button.CommentButton
import dev.kioba.design.system.button.LikeButton
import dev.kioba.design.system.button.ProfileButton
import dev.kioba.design.system.button.RepostButton
import dev.kioba.design.system.component.Avatar
import dev.kioba.design.system.post.PostContent
import dev.kioba.design.system.post.PostItemBox
import dev.kioba.design.system.post.PostTitle
import dev.kioba.design.system.theme.appBarTitle
import dev.kioba.feature.feed.data.FeedAnchor
import dev.kioba.feature.feed.data.navigateToDetails
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
    Box(Modifier.fillMaxSize()) {
      val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

      Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { FeedAppBar(state, scrollBehavior) },
        contentWindowInsets = WindowInsets.statusBars,
      ) { paddingValues ->
        FeedContent(
          state = state,
          modifier = Modifier.padding(paddingValues),
        )
      }

      LoadingUi(state)

      ErrorUI(state)

    }
  }
}

@Composable
private fun BoxScope.LoadingUi(
  state: FeedState,
) {
  AnimatedVisibility(
    visible = state.feedLoading || state.usersLoading,
    modifier = Modifier.Companion.align(Alignment.Center),
  ) {
    CircularProgressIndicator(
      modifier = Modifier.Companion
        .align(Alignment.Center),
    )
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
      bottom = 16.dp + WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding(),
    ),
    modifier = modifier,
  ) {
    itemsIndexed(
      items = state.combined,
      key = { _, item -> item.post.id.value },
      contentType = { _, _ -> CombinedFeedItem::class },
    ) { index, item ->
      val update = anchor<FeedAnchor> { navigateToDetails(item.post.id) }
      PostItemBox(
        avatar = { AnimatedPostAvatar(item) },
        title = { AnimatedPostTitle(item) },
        description = {
          PostContent(
            title = item.post.title,
            content = item.post.body,
            maxLines = 5,
          )
        },
        actions = { PostActionBar() },
        modifier = Modifier
          .clickable { update() }
          .padding(top = 8.dp)
          .padding(horizontal = 16.dp),
      )
      if (index != state.combined.lastIndex) {
        HorizontalDivider()
      }
    }
  }
}

@Composable
private fun AnimatedPostTitle(
  item: CombinedFeedItem,
) {
  AnimatedVisibility(item.user != null) {
    item.user?.let { user -> PostTitle(user.name.value) }
  }
}

@Composable
private fun AnimatedPostAvatar(
  item: CombinedFeedItem,
) {
  AnimatedVisibility(item.avatar != null) {
    item.avatar?.let { avatarUrl -> Avatar(avatarUrl) }
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
private fun PostActionBar() {
  Row(
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
