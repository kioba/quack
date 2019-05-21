package io.github.kioba.detail

import android.view.ViewGroup
import io.github.kioba.detail.R.layout

class CommentViewHolder(parent: ViewGroup, detailAdapter: DetailAdapter) : DetailViewHolder(
  layout.view_detail_comment, parent, detailAdapter
) {
  override fun accept(visitor: DetailDataHolder) {
    visitor.visit(this)
  }

}
