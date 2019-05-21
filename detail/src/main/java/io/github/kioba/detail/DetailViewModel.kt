package io.github.kioba.detail

import androidx.lifecycle.ViewModel
import io.github.kioba.core.MVIViewModel
import io.github.kioba.detail.mvi_models.DetailAction
import io.github.kioba.detail.mvi_models.DetailEvent
import io.github.kioba.detail.mvi_models.DetailIntent
import io.github.kioba.detail.mvi_models.DetailViewState
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer

interface IDetailViewModel : MVIViewModel<DetailIntent, DetailAction, DetailEvent, DetailViewState>

class DetailViewModel : ViewModel(), IDetailViewModel {
  override fun bind(intents: Flowable<DetailIntent>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun state(): Flowable<DetailViewState> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun intentFilter(): FlowableTransformer<DetailIntent, DetailIntent> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun reducer(state: DetailViewState, result: DetailAction): DetailViewState {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
