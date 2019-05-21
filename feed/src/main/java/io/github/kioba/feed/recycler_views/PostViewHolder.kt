package io.github.kioba.feed.recycler_views

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import io.github.kioba.feed.R
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import kotlinx.android.synthetic.main.view_feed_post.view.*

interface NavigationControl {
  fun animateToDetail(post: Post, /*user: User,*/ view: View, viewRect: Rect)
}

class PostViewHolder(parent: ViewGroup, eventHandler: NavigationControl) :
  FeedViewHolder(R.layout.view_feed_post, parent, eventHandler), PostView {
  override fun setOnClickEventHandling(post: Post/*, user: User*/) {
    itemView.setOnClickListener {
      val viewRect = Rect()
      itemView.getGlobalVisibleRect(viewRect)
      eventHandler.animateToDetail(post, /*user,*/ itemView, viewRect)
    }
  }

  override fun setTitle(title: String) {
    itemView.post_title.text = title
    itemView.transitionName = title
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
  fun setOnClickEventHandling(post: Post/*, user: User*/)
}
