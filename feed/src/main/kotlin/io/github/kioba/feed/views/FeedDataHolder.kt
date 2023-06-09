package io.github.kioba.feed.views

import arrow.core.Option
import io.github.kioba.feed.views.FeedDataHolder.FeedViewType
import io.github.kioba.feed.views.FeedDataHolder.FeedViewType.ErrorType
import io.github.kioba.feed.views.FeedDataHolder.FeedViewType.PostType
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User

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
    view.clear()

    post.fold(
      ifEmpty = {
        view.setTitleLoading()
        view.setBodyLoading()
      },
      ifSome = {
        view.setOnClickEventHandling(it)
        view.setTitle(it.title)
        view.setBody(it.body)
      }
    )

    user.fold(
      ifEmpty = {
        view.setNameLoading()
      },
      ifSome = {
        view.setName(it.name, it.username)
      }
    )

    avatar.fold(
      ifEmpty = {
        view.setAvatarLoading()
      },
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
