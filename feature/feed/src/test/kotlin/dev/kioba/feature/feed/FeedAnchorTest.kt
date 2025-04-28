package dev.kioba.feature.feed

import arrow.core.Either
import dev.kioba.anchor.test.runAnchorTest
import dev.kioba.domain.post.api.syncPosts
import dev.kioba.domain.user.api.syncUsers
import dev.kioba.feature.feed.data.FeedAnchor
import dev.kioba.feature.feed.data.FeedEffects
import dev.kioba.feature.feed.data.feedAnchor
import dev.kioba.feature.feed.data.init
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Before
import org.junit.Test

class FeedAnchorTest {
  private val feedEffects: FeedEffects = mockk()

  @Before
  fun before() {
    mockkStatic("dev.kioba.domain.post.api.PostUseCaseKt")
    mockkStatic("dev.kioba.domain.user.api.UserDomainKt")
  }

  @After
  fun after() {
    unmockkStatic("dev.kioba.domain.post.api.PostUseCaseKt")
    unmockkStatic("dev.kioba.domain.user.api.UserDomainKt")
  }

  @Test
  fun testSuccessfulFetchOnInit() =
    runAnchorTest({ feedAnchor(feedEffects) }) {
      given("mock synchronization") {
        coEvery { feedEffects.syncPosts() } returns Either.Right(emptyList())
        coEvery { feedEffects.syncUsers() } returns Either.Right(emptyList())
      }

      on("initial called", FeedAnchor::init)

      verify("fetch data without updating the state manually") {}
    }

  @Test
  fun testSyncPostsFailOnInit() =
    runAnchorTest({ feedAnchor(feedEffects) }) {
      given("mock synchronization with sync posts returning an error") {
        coEvery { feedEffects.syncPosts() } returns syncPostsError()
      }

      on("initial called", FeedAnchor::init)

      verify("fetch data failed with feed error") {
        assertState {
          copy(
            feedLoading = false,
            usersLoading = false,
            feedError = syncPostsError().leftOrNull()
          )
        }
      }
    }

  @Test
  fun testSyncUsersFailOnInit() =
    runAnchorTest({ feedAnchor(feedEffects) }) {
      given("mock synchronization with sync user returning an error") {
        coEvery { feedEffects.syncPosts() } returns Either.Right(emptyList())
        coEvery { feedEffects.syncUsers() } returns syncUsersError()
      }

      on("initial called", FeedAnchor::init)

      verify("fetch data failed with feed error") {
        assertState {
          copy(
            feedLoading = false,
            usersLoading = false,
            userError = syncUsersError().leftOrNull()
          )
        }
      }
    }
}
