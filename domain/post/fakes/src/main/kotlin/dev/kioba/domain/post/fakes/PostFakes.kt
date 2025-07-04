package dev.kioba.domain.post.fakes

import dev.kioba.domain.post.api.model.Post
import dev.kioba.domain.post.api.model.PostId
import dev.kioba.domain.user.fakes.givenPostBody
import dev.kioba.domain.user.fakes.givenPostTitle
import dev.kioba.domain.user.fakes.givenUserId
import dev.kioba.platform.test.TestDataScope

public fun TestDataScope.givenPost(
  postId: PostId = givenPostIdOne(),
): Post =
  Post(
    body = givenPostBody(),
    id = postId,
    title = givenPostTitle(),
    userId = givenUserId(),
  )


@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenPostId(): PostId =
  PostId(1)

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenPostIdOne(): PostId =
  PostId(1)


@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenPostIdTwo(): PostId =
  PostId(2)


@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenPostIdThree(): PostId =
  PostId(3)
