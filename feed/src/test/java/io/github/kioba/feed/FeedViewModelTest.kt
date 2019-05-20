package io.github.kioba.feed

import io.github.kioba.core.TestSchedulers
import io.github.kioba.feed.mvi_models.FeedState
import io.github.kioba.feed.mvi_models.InitialFeedIntent
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

val Post.Companion.testDefault
  get() = Post(
    id = 1,
    userId = 1,
    title = "This is the day!",
    body = "This is the day you will always remember as the day you almost caught Captain Jack Sparrow"
  )

class FeedViewModelTest {

  private val sdk: io.github.kioba.placeholder.IPlaceholderSdk = mockk()

  private lateinit var viewModel: FeedViewModel
  private lateinit var testFlowable: TestSubscriber<FeedState>

  @Before
  fun setUp() {
    clearMocks(sdk)

    viewModel =
      FeedViewModel(FeedActionProcessor(TestSchedulers(), sdk))
    testFlowable = viewModel.state().test()
  }

  @Test
  fun testInitialSuccess() {

    every { sdk.getFeed() } returns Flowable.just(listOf(Post.testDefault))

    viewModel.bind(Flowable.just(InitialFeedIntent))

    val loading = FeedState()

    testFlowable
      .assertNoErrors()
      .assertValueCount(3)
      .assertValueAt(0, loading)
      .assertValueAt(1, loading)
      .assertValueAt(2, FeedState(feedLoading = false, feed = listOf(Post.testDefault)))
      .dispose()
  }

  @Test
  fun testInitialFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getFeed() } returns Flowable.error(error)

    viewModel.bind(Flowable.just(InitialFeedIntent))

    testFlowable
      .assertNoErrors()
      .assertValueCount(3)
      .assertValueAt(0, FeedState())
      .assertValueAt(1, FeedState())
      .assertValueAt(2, FeedState().copy(feedLoading = false, feedError = error))
      .dispose()
  }

}
