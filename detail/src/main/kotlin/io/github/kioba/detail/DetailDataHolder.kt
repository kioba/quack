package io.github.kioba.detail

import arrow.core.Option
import io.github.kioba.placeholder.network.model.Comment

interface DetailDataHolder {
  fun visit(view: CommentView)
}

data class CommentDataHolder(val comment: Option<Comment>) : DetailDataHolder {
  override fun visit(view: CommentView) {
    comment.fold(
      ifEmpty = view::showCommentLoading,
      ifSome = {
        view.showComment(it.body)
      }
    )
  }
}
