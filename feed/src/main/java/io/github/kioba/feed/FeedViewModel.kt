package io.github.kioba.feed

import androidx.lifecycle.ViewModel
import arrow.core.toOption
import io.github.kioba.core.IActionProcessor
import io.github.kioba.core.IActionProcessor
import io.github.kioba.core.MVIViewModel
import io.github.kioba.feed.mvi_models.CombinedFeed
import io.github.kioba.feed.mvi_models.FeedContentError
import io.github.kioba.feed.mvi_models.FeedContentLoading
import io.github.kioba.feed.mvi_models.FeedContentSuccess
import io.github.kioba.feed.mvi_models.FeedEvent
import io.github.kioba.feed.mvi_models.FeedIntent
import io.github.kioba.feed.mvi_models.FeedResult
import io.github.kioba.feed.mvi_models.FeedState
import io.github.kioba.feed.mvi_models.FeedUserError
import io.github.kioba.feed.mvi_models.FeedUserLoading
import io.github.kioba.feed.mvi_models.FeedUserSuccess
import io.github.kioba.feed.mvi_models.InitialFeedIntent
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.github.kioba.placeholder.json_placeholder.network_models.User
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.processors.PublishProcessor
import javax.inject.Inject

typealias IFeedViewModel = MVIViewModel<FeedIntent, FeedResult, FeedEvent, FeedState>

class FeedViewModel @Inject constructor(
  private val resultProcessor: IActionProcessor<FeedIntent, FeedResult>
) : ViewModel(), IFeedViewModel {
  private val binder: PublishProcessor<FeedIntent> = PublishProcessor.create()

  private val state by lazy {
    binder
      .compose(intentFilter())
      .compose(resultProcessor.actionTransformer)
      .scan(FeedState(), this::reducer)
      .replay(1)
      .autoConnect(0)
  }

  override fun bind(intents: Flowable<FeedIntent>) =
    intents.subscribe(binder)

  override fun state(): Flowable<FeedState> = state

  override fun intentFilter() = FlowableTransformer<FeedIntent, FeedIntent> { stream ->
    stream.publish { shared ->
      Flowable.merge(
        shared.ofType(InitialFeedIntent::class.java).take(1),
        shared.filter { it !is InitialFeedIntent }
      )
    }
  }

  override fun reducer(state: FeedState, result: FeedResult): FeedState = when (result) {
    is FeedContentSuccess -> state.copy(
      feedLoading = false,
      feed = result.value,
      feedError = null,
      combined = createCombined(result.value, state.users)
    )
    is FeedContentError -> state.copy(feedLoading = false, feedError = result.error)
    FeedContentLoading -> state.copy(feedLoading = true)
    is FeedUserSuccess -> {
      val userMap = result.value.map { it.id to it }.toMap()
      state.copy(
        usersLoading = false,
        users = userMap,
        userError = null,
        combined = createCombined(state.feed, userMap)
      )
    }
    is FeedUserError -> state.copy(usersLoading = false, userError = result.error)
    FeedUserLoading -> state.copy(usersLoading = true)
  }

  private fun createCombined(feed: List<Post>, userMap: Map<Int, User>): List<CombinedFeed> =
    feed.map {
      val user = userMap[it.userId].toOption()
      CombinedFeed(
        it,
        user = user,
        avatar = user.map { item -> "https://api.adorable.io/avatars/134/" + item.username })
    }

}

