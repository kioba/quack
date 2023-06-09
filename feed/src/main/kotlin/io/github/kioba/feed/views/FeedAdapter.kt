package io.github.kioba.feed.views
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import io.github.kioba.core.BaseViewHolder
import io.github.kioba.feed.views.FeedDataHolder.FeedViewType
import io.github.kioba.feed.views.FeedDataHolder.FeedViewType.ErrorType
import io.github.kioba.feed.views.FeedDataHolder.FeedViewType.PostType

typealias FeedViewHolder = BaseViewHolder<FeedDataHolder, NavigationControl>

class FeedAdapter(private val feedFragment: NavigationControl) :
  Adapter<FeedViewHolder>() {

  var feed: List<FeedDataHolder> = listOf()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun getItemViewType(position: Int): Int = feed[position].type.ordinal

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder =
    when (FeedViewType.fromPosition(viewType)) {
      PostType -> PostViewHolder(parent, feedFragment)
      ErrorType -> ErrorFeedViewHolder(parent, feedFragment)
    }

  override fun getItemCount(): Int = feed.size

  override fun onBindViewHolder(holder: FeedViewHolder, position: Int) =
    holder.accept(feed[position])

}


