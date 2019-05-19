package io.github.kioba.feed

import io.reactivex.FlowableTransformer

interface IActionProcessor<INTENT, ACTION> {

  val actionTransformer: FlowableTransformer<INTENT, ACTION>
}
