package io.github.kioba.feed

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.kioba.core.BaseViewHolder
import io.github.kioba.feed.FeedAdapter.FeedViewType.Error
import io.github.kioba.feed.FeedAdapter.FeedViewType.Post

interface FeedDataHolder {
  fun visit(view: PostView) {}
  fun visit(view: ErrorView) {}
}

class PostDataHolder : FeedDataHolder {
  override fun visit(view: PostView) {
  }
}

class ErrorFeedDataHolder : FeedDataHolder {
  override fun visit(view: ErrorView) {}
}


typealias FeedViewHolder = BaseViewHolder<FeedDataHolder, FeedAdapter>

class FeedAdapter : RecyclerView.Adapter<FeedViewHolder>() {

  enum class FeedViewType {
    Post,
    Error;

    companion object {
      fun fromPosition(position: Int): FeedViewType = values().getOrElse(position) { Error }
    }
  }

  var feed: List<FeedDataHolder> = listOf(
    PostDataHolder(),
    PostDataHolder(),
    PostDataHolder(),
    PostDataHolder(),
    PostDataHolder(),
    PostDataHolder()
  )
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder =
    when (FeedViewType.fromPosition(viewType)) {
      Post -> PostViewHolder(parent, this)
      Error -> ErrorFeedViewHolder(parent, this)
    }

  override fun getItemCount(): Int = feed.size

  override fun onBindViewHolder(holder: FeedViewHolder, position: Int) =
    holder.accept(feed[position])

}

class PostViewHolder(parent: ViewGroup, eventHandler: FeedAdapter) :
  FeedViewHolder(R.layout.view_feed_post, parent, eventHandler), PostView {

  override fun accept(visitor: FeedDataHolder) = visitor.visit(this)


}


interface PostView

interface ErrorView


class ErrorFeedViewHolder(parent: ViewGroup, eventHandler: FeedAdapter) :
  FeedViewHolder(R.layout.view_feed_error, parent, eventHandler), ErrorView {
  override fun accept(visitor: FeedDataHolder) = visitor.visit(this)
}


