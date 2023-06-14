package io.github.kioba.feed.data

import androidx.lifecycle.ViewModel
import dev.kioba.anchor.AnchorScope
import dev.kioba.anchor.anchorScope
import dev.kioba.anchor.dsl.listen
import dev.kioba.anchor.dsl.reduce
import io.github.kioba.core.IActionProcessor
import io.github.kioba.core.MVIViewModel
import io.github.kioba.feed.model.FeedContentError
import io.github.kioba.feed.model.FeedContentLoading
import io.github.kioba.feed.model.FeedContentSuccess
import io.github.kioba.feed.model.FeedEvent
import io.github.kioba.feed.model.FeedIntent
import io.github.kioba.feed.model.FeedResult
import io.github.kioba.feed.model.FeedState
import io.github.kioba.feed.model.FeedUserError
import io.github.kioba.feed.model.FeedUserLoading
import io.github.kioba.feed.model.FeedUserSuccess
import io.github.kioba.feed.model.InitialFeedIntent
import io.github.kioba.placeholder.IPlaceholderSdk
import io.github.kioba.placeholder.post.Post
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.processors.PublishProcessor
import kotlinx.coroutines.reactive.asFlow
import javax.inject.Inject


public typealias FeedScope = AnchorScope<FeedState, FeedEffectsScope>

class FeedEffectsScope @Inject constructor(
  val sdk: IPlaceholderSdk,
)

public fun feedScope(
  effectsScope: FeedEffectsScope,
): FeedScope =
  anchorScope(
    initialState = { FeedState() },
    effectScope = { effectsScope },
    init = { init() },
  ) {
    listen { sdk.getFeed().asFlow() }
      .anchor<FeedScope, List<Post>> { posts -> feedAnchor(posts) }
  }

context(FeedScope)
fun init() {
  reduce { copy(feedLoading = true) }
}

context(FeedScope)
fun feedAnchor(
  posts: List<Post>,
) {
  reduce { loadedSuccessfully(posts) }
}


typealias IFeedViewModel = MVIViewModel<FeedIntent, FeedResult, FeedEvent, FeedState>

class FeedViewModel @Inject constructor(
  private val resultProcessor: IActionProcessor<FeedIntent, FeedResult>,
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
    is FeedContentSuccess -> state.loadedSuccessfully(result.value)

    is FeedContentError -> state.copy(feedLoading = false, feedError = result.error)
    FeedContentLoading -> state.copy(feedLoading = true)
    is FeedUserSuccess -> {
      val userMap = result.value.associateBy { it.id }
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

}