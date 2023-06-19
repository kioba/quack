package io.github.kioba.domain.post.fakes

import io.github.kioba.domain.post.api.model.Post
import io.github.kioba.domain.post.api.model.PostId
import io.github.kioba.domain.user.fakes.givenPostBody
import io.github.kioba.domain.user.fakes.givenPostTitle
import io.github.kioba.domain.user.fakes.givenUserId
import io.github.kioba.platform.test.TestDataScope

context(TestDataScope)
  public fun givenPost(): Post =
  Post(
    body = givenPostBody(),
    id = givenPostId(),
    title = givenPostTitle(),
    userId = givenUserId(),
  )

context(TestDataScope)
  public fun givenPostId(): PostId =
  PostId(1)
