package io.github.kioba.feed

import io.github.kioba.feed.mvi_models.FeedError
import io.github.kioba.feed.mvi_models.FeedIntent
import io.github.kioba.feed.mvi_models.FeedLoading
import io.github.kioba.feed.mvi_models.FeedResult
import io.github.kioba.feed.mvi_models.FeedSuccess
import io.github.kioba.feed.mvi_models.InitialFeedIntent
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler

class FeedActionProcessor constructor(
  private val mainScheduler: Scheduler,
  private val ioScheduler: Scheduler,
  sdk: IPlaceholderSdk
) :
  IActionProcessor<FeedIntent, FeedResult> {

  private val loadContent = FlowableTransformer<InitialFeedIntent, FeedResult> { stream ->
    stream.switchMap {
      sdk.getFeed()
        .map<FeedResult>(::FeedSuccess)
        .onErrorReturn(::FeedError)
        .subscribeOn(ioScheduler)
        .observeOn(mainScheduler)
        .startWith(FeedLoading)
    }
  }

  override val actionTransformer = FlowableTransformer<FeedIntent, FeedResult> { stream ->
    stream.publish { share ->
      share.ofType(InitialFeedIntent::class.java).compose(loadContent)
    }
  }

}
