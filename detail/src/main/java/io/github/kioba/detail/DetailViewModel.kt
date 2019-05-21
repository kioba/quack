package io.github.kioba.detail

import androidx.lifecycle.ViewModel
import io.github.kioba.core.MVIViewModel
import io.github.kioba.detail.mvi_models.DetailCommentError
import io.github.kioba.detail.mvi_models.DetailCommentLoading
import io.github.kioba.detail.mvi_models.DetailCommentSuccess
import io.github.kioba.detail.mvi_models.DetailEvent
import io.github.kioba.detail.mvi_models.DetailIntent
import io.github.kioba.detail.mvi_models.DetailPostSuccess
import io.github.kioba.detail.mvi_models.DetailResult
import io.github.kioba.detail.mvi_models.DetailUserError
import io.github.kioba.detail.mvi_models.DetailUserLoading
import io.github.kioba.detail.mvi_models.DetailUserSuccess
import io.github.kioba.detail.mvi_models.DetailViewState
import io.github.kioba.detail.mvi_models.InitialDetailIntent
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

interface IDetailViewModel : MVIViewModel<DetailIntent, DetailResult, DetailEvent, DetailViewState>

class DetailViewModel @Inject constructor(private val processor: IDetailActionProcessor) :
  ViewModel(),
  IDetailViewModel {
  private val binder: PublishProcessor<DetailIntent> = PublishProcessor.create()

  private val state by lazy {
    binder
      .compose(intentFilter())
      .compose(processor.actionTransformer)
      .scan(DetailViewState(), this::reducer)
      .replay(1)
      .autoConnect(0)
  }

  override fun bind(intents: Flowable<DetailIntent>) =
    intents.subscribe(binder)

  override fun state() = state


  override fun intentFilter() = FlowableTransformer<DetailIntent, DetailIntent> { stream ->
    stream.publish { shared ->
      Flowable.merge(
        shared.ofType(InitialDetailIntent::class.java).take(1),
        shared.filter { it !is InitialDetailIntent }
      )
    }
  }

  override fun reducer(state: DetailViewState, result: DetailResult): DetailViewState =
    when (result) {
      is DetailCommentSuccess -> state.copy(
        isCommentsLoading = false,
        comments = result.comments,
        commentError = null
      )
      is DetailCommentError -> state.copy(isCommentsLoading = false, commentError = result.error)
      DetailCommentLoading -> state.copy(isCommentsLoading = true)
      is DetailUserSuccess -> state.copy(
        isUserLoading = false,
        user = result.user,
        userError = null
      )
      is DetailUserError -> state.copy(isUserLoading = false, userError = result.error)
      DetailUserLoading -> state.copy(isUserLoading = true)
      is DetailPostSuccess -> state.copy(post = result.post)
    }
}
