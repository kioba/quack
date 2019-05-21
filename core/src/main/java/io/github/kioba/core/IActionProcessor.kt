package io.github.kioba.core

import io.reactivex.FlowableTransformer

interface IActionProcessor<INTENT, ACTION> {

  val actionTransformer: FlowableTransformer<INTENT, ACTION>
}
