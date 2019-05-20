package io.github.kioba.feed.recycler_views

import arrow.core.Option
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType.ErrorType
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType.PostType
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.github.kioba.placeholder.json_placeholder.network_models.User

interface FeedDataHolder {

  enum class FeedViewType {
    PostType,
    ErrorType;

    companion object {
      fun fromPosition(position: Int): FeedViewType = values().getOrElse(position) { ErrorType }
    }
  }

  val type: FeedViewType

  fun visit(view: PostView) {}
  fun visit(view: ErrorView) {}
}

data class PostDataHolder(
  val post: Option<Post>,
  val user: Option<User>,
  val avatar: Option<String>
) : FeedDataHolder {
  override val type: FeedViewType = PostType

  override fun visit(view: PostView) {
//    view.setOnClickEventHandling(post)

    post.fold(
      ifEmpty = {},
      ifSome = {
        view.setTitle(it.title)
        view.setBody(it.body)
      }
    )

    user.fold(
      ifEmpty = {},
      ifSome = {
        view.setName(it.name, it.username)
      }
    )

    avatar.fold(
      ifEmpty = {},
      ifSome = {
        view.setAvatar(it)
      }
    )
  }
}

class ErrorFeedDataHolder : FeedDataHolder {
  override val type: FeedViewType = ErrorType

  override fun visit(view: ErrorView) {}
}
