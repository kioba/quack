package io.github.kioba.detail

import io.github.kioba.core.TestSchedulers
import io.github.kioba.detail.mvi_models.DetailViewState
import io.github.kioba.detail.mvi_models.InitialDetailIntent
import io.github.kioba.placeholder.PlaceholderSdk
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {

  internal lateinit var sdk: PlaceholderSdk

  private lateinit var viewModel: DetailViewModel
  private lateinit var testSubscriber: TestSubscriber<DetailViewState>

  @Before
  fun setUp() {
    sdk = mockk()

    viewModel =
      DetailViewModel(DetailActionProcessor(TestSchedulers(), sdk))
    testSubscriber = viewModel.state().test()
  }

  @Test
  fun testInitialSuccess() {

    every { sdk.getUser(givenPost().userId) } returns Flowable.just(givenUser())
    every { sdk.getComments(givenPost().id) } returns Flowable.just(listOf(givenComment()))

    viewModel.bind(Flowable.just(InitialDetailIntent(givenPost())))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = givenPost()))
      .assertValueAt(
        2,
        DetailViewState(
          post = givenPost(),
          isUserLoading = false,
          userError = null,
          user = givenUser()
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = givenPost(),
          isCommentsLoading = false,
          commentError = null,
          comments = listOf(givenComment()),
          isUserLoading = false,
          userError = null,
          user = givenUser()
        )
      )
      .dispose()
  }

  @Test
  fun testUserFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getUser(givenPost().userId) } returns Flowable.error(error)
    every { sdk.getComments(givenPost().id) } returns Flowable.just(listOf(givenComment()))

    viewModel.bind(Flowable.just(InitialDetailIntent(givenPost())))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = givenPost()))
      .assertValueAt(
        2,
        DetailViewState(
          post = givenPost(),
          isUserLoading = false,
          userError = error
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = givenPost(),
          isCommentsLoading = false,
          commentError = null,
          comments = listOf(givenComment()),
          isUserLoading = false,
          userError = error
        )
      )
      .dispose()
  }

  @Test
  fun testCommentsFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getUser(givenPost().userId) } returns Flowable.just(givenUser())
    every { sdk.getComments(givenPost().id) } returns Flowable.error(error)

    viewModel.bind(Flowable.just(InitialDetailIntent(givenPost())))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = givenPost()))
      .assertValueAt(
        2,
        DetailViewState(
          post = givenPost(),
          isUserLoading = false,
          userError = null,
          user = givenUser()
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = givenPost(),
          isCommentsLoading = false,
          commentError = error,
          isUserLoading = false,
          userError = null,
          user = givenUser()
        )
      )
      .dispose()
  }

  @Test
  fun testBothUserAndCommentsFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getUser(givenPost().userId) } returns Flowable.error(error)
    every { sdk.getComments(givenPost().id) } returns Flowable.error(error)

    viewModel.bind(Flowable.just(InitialDetailIntent(givenPost())))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = givenPost()))
      .assertValueAt(
        2,
        DetailViewState(
          post = givenPost(),
          isUserLoading = false,
          userError = error
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = givenPost(),
          isCommentsLoading = false,
          commentError = error,
          isUserLoading = false,
          userError = error
        )
      )
      .dispose()
  }

}
