package io.github.kioba.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import io.github.kioba.core.BaseViewHolder

typealias DetailViewHolder = BaseViewHolder<DetailDataHolder, DetailAdapter>

class DetailAdapter : ListAdapter<DetailDataHolder, DetailViewHolder>(diffUtil) {

  var comments: List<DetailDataHolder>
    set(value) = submitList(value)
    get() = currentList

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
    CommentViewHolder(parent, this)

  override fun getItemCount(): Int = comments.size

  override fun onBindViewHolder(holder: DetailViewHolder, position: Int) =
    holder.accept(comments[position])

  companion object {
    val diffUtil = object : DiffUtil.ItemCallback<DetailDataHolder>() {
      override fun areItemsTheSame(oldItem: DetailDataHolder, newItem: DetailDataHolder): Boolean =
        true


      override fun areContentsTheSame(
        oldItem: DetailDataHolder,
        newItem: DetailDataHolder
      ): Boolean = oldItem is CommentDataHolder && newItem is CommentDataHolder &&
        oldItem.comment.orNull()?.id == newItem.comment.orNull()?.id
    }
  }
}
