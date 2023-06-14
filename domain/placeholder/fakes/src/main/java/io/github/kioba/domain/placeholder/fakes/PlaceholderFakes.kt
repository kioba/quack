package io.github.kioba.domain.placeholder.fakes

import io.github.kioba.placeholder.model.Avatar
import io.github.kioba.placeholder.model.Email
import io.github.kioba.placeholder.model.Name
import io.github.kioba.placeholder.model.PostId
import io.github.kioba.placeholder.model.UserId
import io.github.kioba.placeholder.model.UserName
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User
import io.github.kioba.platform.test.TestDataScope


fun TestDataScope.givenPost(): Post =
  Post(
    body = givenPostBody(),
    id = givenPostId().value,
    title = givenPostTitle(),
    userId = givenUserId().value,
  )

@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenPostId(): PostId =
  PostId(1)

@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenUserId(): UserId =
  UserId(1)

@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenName(): Name =
  Name("John Doe")

@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenEmail(): Email =
  Email("JohnDoe@example.com")

@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenAvatar(): Avatar =
  Avatar("https://via.placeholder.com/150/56a8c2")


@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenUserName(): UserName =
  UserName("john_doe")

fun TestDataScope.givenUser(): User =
  User(
    id = givenUserId().value,
    username = givenUserName().value,
    name = givenName().value,
    email = givenEmail().value,
    avatar = givenAvatar().value,
  )

@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenPostTitle(): String =
  "Lorem ipsum dolor sit amet"

@Suppress("UnusedReceiverParameter")
fun TestDataScope.givenPostBody(): String =
  "Vivamus convallis massa nec nisl vehicula efficitur. " +
    "Mauris a semper nibh, sed pellentesque neque. " +
    "Proin nec consectetur magna. " +
    "Vivamus scelerisque lacus vitae quam porta imperdiet. " +
    "Nullam maximus a tortor sit amet finibus. Suspendisse in gravida purus. " +
    "Mauris eu diam a sapien consequat ullamcorper interdum non augue. " +
    "Donec turpis ex, fermentum dignissim faucibus ut, dictum sit amet justo."
