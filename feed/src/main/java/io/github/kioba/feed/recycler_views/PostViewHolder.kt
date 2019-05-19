package io.github.kioba.feed.recycler_views

import android.view.ViewGroup
import io.github.kioba.feed.R
import kotlinx.android.synthetic.main.view_feed_post.view.*

class PostViewHolder(parent: ViewGroup, eventHandler: FeedAdapter) :
  FeedViewHolder(R.layout.view_feed_post, parent, eventHandler), PostView {
  override fun setTitle(title: String) {
    itemView.post_title.text = title
  }

  override fun setBody(body: String) {
    itemView.post_body.text = body
  }

  override fun setAvatar(url: String) {
  }

  override fun setName(name: String, nickname: String) {
    itemView.post_user_name.text = "%s %s".format(name, nickname)
  }

  override fun accept(visitor: FeedDataHolder) = visitor.visit(this)
}


interface PostView {
  fun setTitle(title: String)
  fun setBody(body: String)
  fun setAvatar(url: String)
  fun setName(name: String, nickname: String)
}
