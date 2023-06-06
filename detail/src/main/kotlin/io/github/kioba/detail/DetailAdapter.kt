package io.github.kioba.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import io.github.kioba.core.BaseViewHolder

typealias DetailViewHolder = BaseViewHolder<DetailDataHolder, DetailAdapter>

class DetailAdapter : Adapter<DetailViewHolder>() {

  var comments: List<DetailDataHolder> = listOf()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder =
    CommentViewHolder(parent, this)

  override fun getItemCount(): Int = comments.size

  override fun onBindViewHolder(holder: DetailViewHolder, position: Int) =
    holder.accept(comments[position])
}
