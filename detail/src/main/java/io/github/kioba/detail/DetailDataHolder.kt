package io.github.kioba.detail

import io.github.kioba.placeholder.json_placeholder.network_models.Comment

interface DetailDataHolder {
  fun visit(holder: CommentViewHolder)
}

data class CommentsDataHolder(val comment: Comment) : DetailDataHolder {
  override fun visit(holder: CommentViewHolder) {

  }
}
