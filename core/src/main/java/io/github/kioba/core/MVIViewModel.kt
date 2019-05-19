package io.github.kioba.core

import androidx.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.processors.PublishProcessor

abstract class MVIViewModel<INTENT, ACTION, EVENT, STATE> : ViewModel() {

  abstract val binder: PublishProcessor<INTENT>

  abstract fun state(): Flowable<STATE>

  abstract fun intentFilter(): FlowableTransformer<INTENT, INTENT>
  abstract fun reducer(state: STATE, result: ACTION): STATE

}
