package io.github.kioba.detail

import io.github.kioba.core.IActionProcessor
import io.github.kioba.detail.mvi_models.DetailIntent
import io.github.kioba.detail.mvi_models.DetailResult
import io.reactivex.FlowableTransformer

class DetailActionProcessor : IActionProcessor<DetailIntent, DetailResult> {
  override val actionTransformer: FlowableTransformer<DetailIntent, DetailResult>
    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}
