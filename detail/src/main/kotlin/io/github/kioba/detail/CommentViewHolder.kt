package io.github.kioba.detail

import android.view.ViewGroup
import io.github.kioba.detail.R.layout

class CommentViewHolder(parent: ViewGroup, detailAdapter: DetailAdapter) :
  DetailViewHolder(layout.view_detail_comment, parent, detailAdapter), CommentView {
  override fun showCommentLoading() {
//    itemView.detail_comment.background =
//      ColorDrawable(ContextCompat.getColor(itemView.context, color.darker_gray))
//
  }

  override fun showComment(comment: String) {
//    itemView.detail_comment.background = null
//    itemView.detail_comment.text = comment
  }

  override fun accept(visitor: DetailDataHolder) {
    visitor.visit(this)
  }

}

interface CommentView {
  fun showCommentLoading()
  fun showComment(comment: String)
}
