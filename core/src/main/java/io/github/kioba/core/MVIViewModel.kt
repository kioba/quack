package io.github.kioba.core

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer

interface MVIViewModel<INTENT, ACTION, EVENT, STATE> {

  fun bind(intents: Flowable<INTENT>)
  fun state(): Flowable<STATE>
  fun intentFilter(): FlowableTransformer<INTENT, INTENT>
  fun reducer(state: STATE, result: ACTION): STATE

}
