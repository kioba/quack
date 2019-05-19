package io.github.kioba.feed

import io.github.kioba.core.MVIViewModel
import io.github.kioba.feed.mvi_models.FeedError
import io.github.kioba.feed.mvi_models.FeedEvent
import io.github.kioba.feed.mvi_models.FeedIntent
import io.github.kioba.feed.mvi_models.FeedLoading
import io.github.kioba.feed.mvi_models.FeedResult
import io.github.kioba.feed.mvi_models.FeedState
import io.github.kioba.feed.mvi_models.FeedSuccess
import io.github.kioba.feed.mvi_models.InitialFeedIntent
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.processors.PublishProcessor

class FeedViewModel constructor(
  private val resultProcessor: IActionProcessor<FeedIntent, FeedResult>
) : MVIViewModel<FeedIntent, FeedResult, FeedEvent, FeedState>() {
  override val binder: PublishProcessor<FeedIntent> = PublishProcessor.create()

  override fun state(): Flowable<FeedState> =
    binder
      .compose(intentFilter())
      .compose(resultProcessor.actionTransformer)
      .scan(FeedState(), this::reducer)
      .replay(1)
      .autoConnect(0)

  override fun intentFilter() = FlowableTransformer<FeedIntent, FeedIntent> { stream ->
    stream.publish { shared ->
      Flowable.merge(
        shared.ofType(InitialFeedIntent::class.java).take(1),
        shared.filter { it !is InitialFeedIntent }
      )
    }
  }

  override fun reducer(state: FeedState, result: FeedResult): FeedState = when (result) {
    is FeedSuccess -> state.copy(loading = false, feed = result.value, error = null)
    is FeedError -> state.copy(loading = false, error = result.error)
    FeedLoading -> state.copy(loading = true)
  }

}

