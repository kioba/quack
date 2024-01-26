package io.github.kioba.domain.post.fakes

import io.github.kioba.domain.post.api.model.Post
import io.github.kioba.domain.post.api.model.PostId
import io.github.kioba.domain.user.fakes.givenPostBody
import io.github.kioba.domain.user.fakes.givenPostTitle
import io.github.kioba.domain.user.fakes.givenUserId
import io.github.kioba.platform.test.TestDataScope

context(TestDataScope)
public fun givenPost(
  postId: PostId = givenPostIdOne(),
): Post =
  Post(
    body = givenPostBody(),
    id = postId,
    title = givenPostTitle(),
    userId = givenUserId(),
  )

context(TestDataScope)
public fun givenPostIdOne(): PostId =
  PostId(1)

context(TestDataScope)
public fun givenPostIdTwo(): PostId =
  PostId(2)

context(TestDataScope)
public fun givenPostIdThree(): PostId =
  PostId(3)
