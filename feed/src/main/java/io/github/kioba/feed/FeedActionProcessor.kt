package io.github.kioba.feed

import io.github.kioba.core.IActionProcessor
import io.github.kioba.core.ISchedulers
import io.github.kioba.feed.mvi_models.FeedError
import io.github.kioba.feed.mvi_models.FeedIntent
import io.github.kioba.feed.mvi_models.FeedLoading
import io.github.kioba.feed.mvi_models.FeedResult
import io.github.kioba.feed.mvi_models.FeedSuccess
import io.github.kioba.feed.mvi_models.InitialFeedIntent
import io.github.kioba.placeholder.IPlaceholderSdk
import io.reactivex.FlowableTransformer
import javax.inject.Inject

class FeedActionProcessor @Inject constructor(
  private val schedulers: ISchedulers,
  sdk: IPlaceholderSdk
) :
  IActionProcessor<FeedIntent, FeedResult> {

  private val loadContent = FlowableTransformer<InitialFeedIntent, FeedResult> { stream ->
    stream.switchMap {
      sdk.getFeed()
        .map<FeedResult>(::FeedSuccess)
        .onErrorReturn(::FeedError)
        .subscribeOn(schedulers.io)
        .observeOn(schedulers.main)
        .startWith(FeedLoading)
    }
  }

  override val actionTransformer = FlowableTransformer<FeedIntent, FeedResult> { stream ->
    stream.publish { share ->
      share.ofType(InitialFeedIntent::class.java).compose(loadContent)
    }
  }

}
