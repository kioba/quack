package io.github.kioba.feed.data

import io.github.kioba.core.IActionProcessor
import io.github.kioba.core.ISchedulers
import io.github.kioba.feed.model.FeedContentError
import io.github.kioba.feed.model.FeedContentLoading
import io.github.kioba.feed.model.FeedContentSuccess
import io.github.kioba.feed.model.FeedIntent
import io.github.kioba.feed.model.FeedResult
import io.github.kioba.feed.model.FeedUserError
import io.github.kioba.feed.model.FeedUserLoading
import io.github.kioba.feed.model.FeedUserSuccess
import io.github.kioba.feed.model.InitialFeedIntent
import io.github.kioba.placeholder.IPlaceholderSdk
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import javax.inject.Inject

class FeedActionProcessor @Inject constructor(
  private val schedulers: ISchedulers,
  sdk: IPlaceholderSdk
) :
  IActionProcessor<FeedIntent, FeedResult> {

  private val loadFeed = FlowableTransformer<InitialFeedIntent, FeedResult> { stream ->
    stream.switchMap {
      sdk.getFeed()
        .map<FeedResult>(::FeedContentSuccess)
        .onErrorReturn(::FeedContentError)
        .subscribeOn(schedulers.io)
        .observeOn(schedulers.main)
        .startWith(FeedContentLoading)
    }
  }

  private val loadUsers = FlowableTransformer<InitialFeedIntent, FeedResult> { stream ->
    stream.switchMap {
      sdk.getUsers()
        .map<FeedResult>(::FeedUserSuccess)
        .onErrorReturn(::FeedUserError)
        .subscribeOn(schedulers.io)
        .observeOn(schedulers.main)
        .startWith(FeedUserLoading)
    }
  }

  override val actionTransformer = FlowableTransformer<FeedIntent, FeedResult> { stream ->
    stream.publish { share ->
      Flowable.merge(
        share.ofType(InitialFeedIntent::class.java).compose(loadFeed),
        share.ofType(InitialFeedIntent::class.java).compose(loadUsers)
      )
    }
  }

}
