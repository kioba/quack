package io.github.kioba.detail

import io.github.kioba.core.TestSchedulers
import io.github.kioba.detail.mvi_models.DetailViewState
import io.github.kioba.detail.mvi_models.InitialDetailIntent
import io.github.kioba.placeholder.IPlaceholderSdk
import io.github.kioba.placeholder.network.network_models.Comment
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test

private val Comment.Companion.testDefault: Comment
  get() = Comment(
    body = "body",
    email = "email",
    id = 1,
    name = "name",
    postId = 1
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

class DetailViewModelTest {

  private val sdk: IPlaceholderSdk = mockk()

  private lateinit var viewModel: DetailViewModel
  private lateinit var testSubscriber: TestSubscriber<DetailViewState>

  @Before
  fun setUp() {
    clearMocks(sdk)

    viewModel =
      DetailViewModel(DetailActionProcessor(TestSchedulers(), sdk))
    testSubscriber = viewModel.state().test()
  }

  @Test
  fun testInitialSuccess() {

    every { sdk.getUser(Post.testDefault.userId) } returns Flowable.just(User.testDefault)
    every { sdk.getComments(Post.testDefault.id) } returns Flowable.just(listOf(Comment.testDefault))

    viewModel.bind(Flowable.just(InitialDetailIntent(Post.testDefault)))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = Post.testDefault))
      .assertValueAt(
        2,
        DetailViewState(
          post = Post.testDefault,
          isUserLoading = false,
          userError = null,
          user = User.testDefault
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = Post.testDefault,
          isCommentsLoading = false,
          commentError = null,
          comments = listOf(Comment.testDefault),
          isUserLoading = false,
          userError = null,
          user = User.testDefault
        )
      )
      .dispose()
  }

  @Test
  fun testUserFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getUser(Post.testDefault.userId) } returns Flowable.error(error)
    every { sdk.getComments(Post.testDefault.id) } returns Flowable.just(listOf(Comment.testDefault))

    viewModel.bind(Flowable.just(InitialDetailIntent(Post.testDefault)))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = Post.testDefault))
      .assertValueAt(
        2,
        DetailViewState(
          post = Post.testDefault,
          isUserLoading = false,
          userError = error
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = Post.testDefault,
          isCommentsLoading = false,
          commentError = null,
          comments = listOf(Comment.testDefault),
          isUserLoading = false,
          userError = error
        )
      )
      .dispose()
  }

  @Test
  fun testCommentsFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getUser(Post.testDefault.userId) } returns Flowable.just(User.testDefault)
    every { sdk.getComments(Post.testDefault.id) } returns Flowable.error(error)

    viewModel.bind(Flowable.just(InitialDetailIntent(Post.testDefault)))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = Post.testDefault))
      .assertValueAt(
        2,
        DetailViewState(
          post = Post.testDefault,
          isUserLoading = false,
          userError = null,
          user = User.testDefault
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = Post.testDefault,
          isCommentsLoading = false,
          commentError = error,
          isUserLoading = false,
          userError = null,
          user = User.testDefault
        )
      )
      .dispose()
  }

  @Test
  fun testBothUserAndCommentsFailure() {

    val error = IllegalStateException("ErrorType Test")

    every { sdk.getUser(Post.testDefault.userId) } returns Flowable.error(error)
    every { sdk.getComments(Post.testDefault.id) } returns Flowable.error(error)

    viewModel.bind(Flowable.just(InitialDetailIntent(Post.testDefault)))

    testSubscriber
      .assertNoErrors()
      .assertValueCount(4)
      .assertValueAt(0, DetailViewState())
      .assertValueAt(1, DetailViewState(post = Post.testDefault))
      .assertValueAt(
        2,
        DetailViewState(
          post = Post.testDefault,
          isUserLoading = false,
          userError = error
        )
      )
      .assertValueAt(
        3,
        DetailViewState(
          post = Post.testDefault,
          isCommentsLoading = false,
          commentError = error,
          isUserLoading = false,
          userError = error
        )
      )
      .dispose()
  }

}
