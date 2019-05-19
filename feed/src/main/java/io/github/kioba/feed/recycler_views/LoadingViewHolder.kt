package io.github.kioba.feed.recycler_views

import android.view.ViewGroup
import io.github.kioba.feed.R

class LoadingViewHolder(parent: ViewGroup, eventHandler: FeedAdapter) :
  FeedViewHolder(R.layout.view_feed_post, parent, eventHandler), LoadingView {

  override fun accept(visitor: FeedDataHolder) = visitor.visit(this)
}


interface LoadingView
