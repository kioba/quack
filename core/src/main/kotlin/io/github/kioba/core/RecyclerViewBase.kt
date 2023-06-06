package io.github.kioba.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<B, out V>
  (layoutId: Int, parent: ViewGroup, override val eventHandler: V) :
  InflaterViewHolder(layoutId, parent),
  ViewHolderEventHandler<V>,
  ViewHolderVisitor<B>

abstract class InflaterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  constructor(layoutId: Int, parent: ViewGroup) :
    this(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
}

interface ViewHolderEventHandler<out T> {
  val eventHandler: T
}

interface ViewHolderVisitor<T> {
  fun accept(visitor: T)
}
