package io.github.kioba.detail

import io.github.kioba.placeholder.network.model.Comment
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User

internal fun givenComment(): Comment =
  Comment(
    body = "body",
    email = "email",
    id = 1,
    name = "name",
    postId = 1
  )

internal fun givenPost(): Post =
  Post(
    id = 1,
    userId = 1,
    title = "This is the day!",
    body = "This is the day you will always remember" +
      " as the day you almost caught Captain Jack Sparrow"
  )

fun givenUser(): User =
  User(
    email = "jack@pearl.com",
    id = 1,
    name = "Jack Sparrow",
    username = "Pirate",
    avatar = "BlackFlag"
  )