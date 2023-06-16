package io.github.kioba.feed

import io.github.kioba.core.TestSchedulers
import io.github.kioba.feed.data.FeedViewModel
import io.github.kioba.feed.model.CombinedFeedItem
import io.github.kioba.feed.model.FeedState
import io.github.kioba.feed.model.InitialFeedIntent
import io.github.kioba.placeholder.PlaceholderSdk
import io.github.kioba.placeholder.network.model.Geo
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

private val Geo.Companion.testDefault: Geo
  get() = Geo(
    lat = "",
    lng = ""
  )

val Post.Companion.testDefault
  get() = Post(
    id = 1,
    userId = 1,
    title = "This is the day!",
    body = "This is the day you will always remember as the day you almost caught Captain Jack Sparrow"
  )

val User.Companion.testDefault
  get() = User(
    email = "jack@pearl.com",
    id = 1,
    name = "Jack Sparrow",
    username = "Pirate",
    avatar = "BlackFlag"
  )

class FeedViewModelTest {

  private val sdk: PlaceholderSdk = mockk()

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
    every { sdk.getUsers() } returns Flowable.just(listOf(User.testDefault))

    viewModel.bind(Flowable.just(InitialFeedIntent))

    val loading = FeedState()

    testFlowable
      .assertNoErrors()
      .assertValueCount(5)
      .assertValueAt(0, loading)
      .assertValueAt(1, loading)
      .assertValueAt(
        2, FeedState(
          feedLoading = false, feed = listOf(Post.testDefault), combined = listOf(
            CombinedFeedItem(post = Post.testDefault, user = null, avatar = null)
          )
        )
      )
      .dispose()
  }

  @Test
  fun testInitialFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getFeed() } returns Flowable.error(error)
    every { sdk.getUsers() } returns Flowable.just(listOf(User.testDefault))

    viewModel.bind(Flowable.just(InitialFeedIntent))

    testFlowable
      .assertNoErrors()
      .assertValueCount(5)
      .assertValueAt(0, FeedState())
      .assertValueAt(1, FeedState())
      .assertValueAt(2, FeedState().copy(feedLoading = false, feedError = error))
      .dispose()
  }

}
