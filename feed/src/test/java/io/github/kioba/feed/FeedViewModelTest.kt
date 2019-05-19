package io.github.kioba.feed

import io.github.kioba.feed.mvi_models.FeedState
import io.github.kioba.feed.mvi_models.InitialFeedIntent
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test


class FeedViewModelTest {

  private val sdk: IPlaceholderSdk = mockk()

  private lateinit var viewModel: FeedViewModel
  private lateinit var testFlowable: TestSubscriber<FeedState>

  @Before
  fun setUp() {
    clearMocks(sdk)

    viewModel =
      FeedViewModel(FeedActionProcessor(Schedulers.trampoline(), Schedulers.trampoline(), sdk))
    testFlowable = viewModel.state().test()
  }

  @Test
  fun testInitialSuccess() {

    every { sdk.getFeed() } returns Flowable.just(listOf(1))

    viewModel.binder.onNext(InitialFeedIntent)

    val loading = FeedState()

    testFlowable
      .assertNoErrors()
      .assertValueCount(3)
      .assertValueAt(0, loading)
      .assertValueAt(1, loading)
      .assertValueAt(2, FeedState(loading = false, feed = listOf(1)))
      .dispose()
  }

  @Test
  fun testInitialFailure() {

    val error = IllegalStateException("Error Test")

    every { sdk.getFeed() } returns Flowable.error(error)

    viewModel.binder.onNext(InitialFeedIntent)

    testFlowable
      .assertNoErrors()
      .assertValueCount(3)
      .assertValueAt(0, FeedState())
      .assertValueAt(1, FeedState())
      .assertValueAt(2, FeedState().copy(loading = false, error = error))
      .dispose()
  }

}
