package io.github.kioba.feed.recycler_views

import android.view.ViewGroup
import io.github.kioba.feed.R

interface ErrorView


class ErrorFeedViewHolder(parent: ViewGroup, eventHandler: NavigationControl) :
  FeedViewHolder(R.layout.view_feed_error, parent, eventHandler), ErrorView {
  override fun accept(visitor: FeedDataHolder) = visitor.visit(this)
}
