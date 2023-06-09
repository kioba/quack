package io.github.kioba.feed

import arrow.core.Option
import io.github.kioba.feed.views.PostDataHolder
import io.github.kioba.feed.views.PostView
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verifyOrder
import org.junit.Before
import org.junit.Test

class PostViewDataHolderTest {

  private val view: PostView = mockk()

  @Before
  fun setup() {

  }

  @Test
  fun testDataHolderIsEmpty() {
    every { view.clear() } just runs
    every { view.setBodyLoading() } just runs
    every { view.setTitleLoading() } just runs
    every { view.setAvatarLoading() } just runs
    every { view.setNameLoading() } just runs

    val dataHolder = PostDataHolder(Option.empty(), Option.empty(), Option.empty())

    dataHolder.visit(view)

    verifyOrder {
      view.clear()
      view.setTitleLoading()
      view.setBodyLoading()
      view.setNameLoading()
      view.setAvatarLoading()
    }
  }

  @Test
  fun testDataHolderJustPost() {

    val post = Post.testDefault

    every { view.clear() } just runs
    every { view.setOnClickEventHandling(post) } just runs
    every { view.setTitle(post.title) } just runs
    every { view.setBody(post.body) } just runs
    every { view.setNameLoading() } just runs
    every { view.setAvatarLoading() } just runs

    val dataHolder = PostDataHolder(Option.fromNullable(post), Option.empty(), Option.empty())

    dataHolder.visit(view)

    verifyOrder {
      view.clear()
      view.setOnClickEventHandling(post)
      view.setTitle(post.title)
      view.setBody(post.body)
      view.setNameLoading()
      view.setAvatarLoading()
    }
  }


  @Test
  fun testDataHolderPostWithUser() {

    val post = Post.testDefault
    val user = User.testDefault

    every { view.clear() } just runs
    every { view.setOnClickEventHandling(post) } just runs
    every { view.setTitle(post.title) } just runs
    every { view.setBody(post.body) } just runs
    every { view.setName(user.name, user.username) } just runs
    every { view.setAvatarLoading() } just runs

    val dataHolder =
      PostDataHolder(Option.fromNullable(post), Option.fromNullable(user), Option.empty())

    dataHolder.visit(view)

    verifyOrder {
      view.clear()
      view.setOnClickEventHandling(post)
      view.setTitle(post.title)
      view.setBody(post.body)
      view.setName(user.name, user.username)
      view.setAvatarLoading()
    }
  }

  @Test
  fun testDataHolderPostWithAllData() {

    val post = Post.testDefault
    val user = User.testDefault

    every { view.clear() } just runs
    every { view.setOnClickEventHandling(post) } just runs
    every { view.setTitle(post.title) } just runs
    every { view.setBody(post.body) } just runs
    every { view.setName(user.name, user.username) } just runs
    every { view.setAvatar(user.avatar) } just runs

    val dataHolder = PostDataHolder(
      Option.fromNullable(post),
      Option.fromNullable(user),
      Option.fromNullable(user.avatar)
    )

    dataHolder.visit(view)

    verifyOrder {
      view.clear()
      view.setOnClickEventHandling(post)
      view.setTitle(post.title)
      view.setBody(post.body)
      view.setName(user.name, user.username)
      view.setAvatar(user.avatar)
    }
  }


  @Test
  fun testDataHolderPostWithPostAndAvatar() {

    val post = Post.testDefault
    val user = User.testDefault

    every { view.clear() } just runs
    every { view.setOnClickEventHandling(post) } just runs
    every { view.setTitle(post.title) } just runs
    every { view.setBody(post.body) } just runs
    every { view.setNameLoading() } just runs
    every { view.setAvatar(user.avatar) } just runs

    val dataHolder =
      PostDataHolder(Option.fromNullable(post), Option.empty(), Option.fromNullable(user.avatar))

    dataHolder.visit(view)

    verifyOrder {
      view.clear()
      view.setOnClickEventHandling(post)
      view.setTitle(post.title)
      view.setBody(post.body)
      view.setNameLoading()
      view.setAvatar(user.avatar)
    }
  }
}
