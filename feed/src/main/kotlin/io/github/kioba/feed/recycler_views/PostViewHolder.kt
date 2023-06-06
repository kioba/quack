package io.github.kioba.feed.recycler_views

import android.R.color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import io.github.kioba.feed.R
import io.github.kioba.placeholder.post.Post
import kotlinx.android.synthetic.main.view_feed_post.view.*

interface NavigationControl {
  fun animateToDetail(post: Post, view: View, viewRect: Rect)
}

class PostViewHolder(parent: ViewGroup, eventHandler: NavigationControl) :
  FeedViewHolder(R.layout.view_feed_post, parent, eventHandler), PostView {
  override fun setBodyLoading() {
    itemView.post_body.background =
      ColorDrawable(ContextCompat.getColor(itemView.context, color.darker_gray))
  }

  override fun setTitleLoading() {
    itemView.post_title.background =
      ColorDrawable(ContextCompat.getColor(itemView.context, color.darker_gray))
  }

  override fun setAvatarLoading() {
    itemView.post_avatar.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_face_primary_24dp))
  }

  override fun setNameLoading() {
    itemView.post_user_name.background =
      ColorDrawable(ContextCompat.getColor(itemView.context, color.darker_gray))

  }

  override fun clear() {
    Picasso.get().cancelRequest(itemView.post_avatar)
    itemView.post_body.text = ""
    itemView.post_body.background = null
    itemView.post_title.text = ""
    itemView.post_title.background = null
    itemView.post_user_name.text = ""
    itemView.post_user_name.background = null
    itemView.transitionName = ""

    itemView.setOnClickListener(null)
  }

  override fun setOnClickEventHandling(post: Post) {
    itemView.setOnClickListener {
      val viewRect = Rect()
      itemView.getGlobalVisibleRect(viewRect)
      eventHandler.animateToDetail(post, itemView, viewRect)
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
    Picasso.get()
      .load(url)
      .error(R.drawable.ic_face_primary_24dp)
      .placeholder(R.drawable.ic_face_primary_24dp)
      .fit()
      .centerCrop()
      .into(itemView.post_avatar)
  }

  override fun setName(name: String, nickname: String) {
    itemView.post_user_name.text = "%s @%s".format(name, nickname)
  }

  override fun accept(visitor: FeedDataHolder) = visitor.visit(this)
}


interface PostView {
  fun setTitle(title: String)
  fun setBody(body: String)
  fun setAvatar(url: String)
  fun setName(name: String, nickname: String)
  fun setOnClickEventHandling(post: Post)

  fun clear()

  fun setBodyLoading()
  fun setTitleLoading()
  fun setAvatarLoading()
  fun setNameLoading()
}
