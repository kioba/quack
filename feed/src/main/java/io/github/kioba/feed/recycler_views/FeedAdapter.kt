package io.github.kioba.feed.recycler_views

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.kioba.core.BaseViewHolder
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType.ErrorType
import io.github.kioba.feed.recycler_views.FeedDataHolder.FeedViewType.PostType

typealias FeedViewHolder = BaseViewHolder<FeedDataHolder, NavigationControl>

class FeedAdapter(private val feedFragment: NavigationControl) :
  ListAdapter<FeedDataHolder, FeedViewHolder>(diffFunction) {

  var feed: List<FeedDataHolder>
    set(value) = submitList(value)
    get() = currentList


  override fun getItemViewType(position: Int): Int = feed[position].type.ordinal

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder =
    when (FeedViewType.fromPosition(viewType)) {
      PostType -> PostViewHolder(parent, feedFragment)
      ErrorType -> ErrorFeedViewHolder(parent, feedFragment)
    }

  override fun getItemCount(): Int = feed.size

  override fun onBindViewHolder(holder: FeedViewHolder, position: Int) =
    holder.accept(feed[position])

  companion object {
    val diffFunction = object : DiffUtil.ItemCallback<FeedDataHolder>() {
      override fun areItemsTheSame(oldItem: FeedDataHolder, newItem: FeedDataHolder): Boolean =
        oldItem.type == newItem.type

      override fun areContentsTheSame(oldItem: FeedDataHolder, newItem: FeedDataHolder): Boolean =
        oldItem is PostDataHolder && newItem is PostDataHolder &&
          oldItem.post.orNull() == newItem.post.orNull() && oldItem.user.orNull() == newItem.user.orNull() &&
          oldItem.avatar.orNull() == newItem.avatar.orNull() ||
          oldItem is ErrorFeedDataHolder && newItem is ErrorFeedDataHolder
    }
  }

}


