package dev.kioba.feature.details

import arrow.core.Either
import dev.kioba.anchor.test.runAnchorTest
import dev.kioba.domain.post.api.syncComments
import dev.kioba.domain.post.fakes.givenComment
import dev.kioba.domain.post.fakes.givenCommentOne
import dev.kioba.domain.post.fakes.givenCommentTwo
import dev.kioba.domain.post.fakes.givenPostId
import dev.kioba.domain.post.fakes.givenPostIdOne
import dev.kioba.feature.details.data.DetailsAnchor
import dev.kioba.feature.details.data.DetailsEffects
import dev.kioba.feature.details.data.detailsAnchor
import dev.kioba.feature.details.data.init
import dev.kioba.feature.details.model.DetailsDestination
import dev.kioba.platform.test.TestDataScope
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Before
import org.junit.Test

class DetailsAnchorTest {
  private val detailsEffects: DetailsEffects = mockk {
    coEvery { destination } returns DetailsDestination(TestDataScope.givenPostId())
  }

  @Before
  fun before() {
    mockkStatic("dev.kioba.domain.post.api.PostUseCaseKt")
    mockkStatic("dev.kioba.domain.post.api.CommentUseCaseKt")
    mockkStatic("dev.kioba.domain.user.api.UserDomainKt")
  }

  @After
  fun after() {
    unmockkStatic("dev.kioba.domain.post.api.PostUseCaseKt")
    unmockkStatic("dev.kioba.domain.post.api.CommentUseCaseKt")
    unmockkStatic("dev.kioba.domain.user.api.UserDomainKt")
  }

  @Test
  fun testSuccessfulFetchOnInit() =
    runAnchorTest({ detailsAnchor(detailsEffects) }) {
      with(TestDataScope) {
        given("mock synchronization") {
          coEvery {
            detailsEffects.syncComments(givenPostIdOne())
          } returns Either.Right(listOf(givenComment(), givenCommentOne(), givenCommentTwo()))
        }
      }

      on("initial called", DetailsAnchor::init)

      verify("fetch data without updating the state manually") {}
    }
}
