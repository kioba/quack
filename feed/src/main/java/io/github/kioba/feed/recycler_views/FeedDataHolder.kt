package io.github.kioba.feed.recycler_views

import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType.ErrorType
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType.LoadingType
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType.PostType
import io.github.kioba.placeholder.json_placeholder.network_models.Post

interface FeedDataHolder {

  enum class FeedViewType {
    PostType,
    ErrorType,
    LoadingType;

    companion object {
      fun fromPosition(position: Int): FeedViewType = values().getOrElse(position) { ErrorType }
    }
  }

  val type: FeedViewType

  fun visit(view: PostView) {}
  fun visit(view: ErrorView) {}
  fun visit(view: LoadingView) {}
}

data class PostDataHolder(val post: Post) : FeedDataHolder {
  override val type: FeedViewType = PostType

  override fun visit(view: PostView) {
    view.setTitle(post.title)
    view.setBody(post.body)
    view.setOnClickEventHandling()
  }
}

class ErrorFeedDataHolder : FeedDataHolder {
  override val type: FeedViewType = ErrorType

  override fun visit(view: ErrorView) {}
}

class LoadingFeedDataHolder : FeedDataHolder {
  override val type: FeedViewType = LoadingType

  override fun visit(view: LoadingView) {}
}
