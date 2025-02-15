package dev.kioba.feature.feed.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import dev.kioba.design.system.button.AboutButton
import dev.kioba.design.system.button.ProfileButton
import dev.kioba.design.system.component.Avatar
import dev.kioba.design.system.component.Gap
import dev.kioba.design.system.post.PostItem
import dev.kioba.design.system.theme.appBarTitle
import dev.kioba.feature.feed.model.CombinedFeedItem
import dev.kioba.feature.feed.model.FeedState
import dev.kioba.feature.feed.model.StringsR
import dev.kioba.platform.android.compose.resolve

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
        Scaffold(
            topBar = { FeedAppBar(state) },
            contentWindowInsets = WindowInsets.statusBars,
        ) { paddingValues ->
            FeedContent(state, Modifier.padding(paddingValues))
        }
    }
}

@Composable
private fun FeedContent(
    state: FeedState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
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
private fun FeedAppBar(state: FeedState) {
    TopAppBar(
        navigationIcon = { ProfileButton(url = state.user.avatar.value) {} },
        title = { AppBarTitle(StringsR.app_name.resolve()) },
        actions = { AboutButton(StringsR.about_the_app_creator.resolve()) },
    )
}

@Composable
private fun AppBarTitle(title: String) {
    Text(
        style = MaterialTheme.typography.appBarTitle,
        text = title,
    )
}

@Composable
private fun PostContent(item: CombinedFeedItem) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            style = MaterialTheme.typography.titleSmall,
            text = item.post.title,
        )
        Gap(8.dp)
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = item.post.content,
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
