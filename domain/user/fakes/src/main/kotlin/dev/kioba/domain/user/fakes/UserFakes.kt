package dev.kioba.domain.user.fakes

import dev.kioba.domain.placeholder.user.model.Avatar
import dev.kioba.domain.placeholder.user.model.Email
import dev.kioba.domain.placeholder.user.model.Name
import dev.kioba.domain.placeholder.user.model.User
import dev.kioba.domain.placeholder.user.model.UserId
import dev.kioba.domain.placeholder.user.model.UserName
import dev.kioba.platform.test.TestDataScope

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenUserId(): UserId =
  UserId(1)

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenName(): Name =
  Name("John Doe")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenEmail(): Email =
  Email("JohnDoe@example.com")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenAvatar(): Avatar =
  Avatar("https://via.placeholder.com/150/56a8c2")


@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenUserName(): UserName =
  UserName("john_doe")


public fun TestDataScope.givenUser(): User =
  User(
    id = givenUserId(),
    username = givenUserName(),
    name = givenName(),
    email = givenEmail(),
    avatar = givenAvatar(),
  )


@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenPostTitle(): String =
  "Lorem ipsum dolor sit amet"

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenPostBody(): String =
  "Vivamus convallis massa nec nisl vehicula efficitur. " +
    "Mauris a semper nibh, sed pellentesque neque. " +
    "Proin nec consectetur magna. " +
    "Vivamus scelerisque lacus vitae quam porta imperdiet. " +
    "Nullam maximus a tortor sit amet finibus. Suspendisse in gravida purus. " +
    "Mauris eu diam a sapien consequat ullamcorper interdum non augue. " +
    "Donec turpis ex, fermentum dignissim faucibus ut, dictum sit amet justo."
