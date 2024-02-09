package io.github.kioba.domain.user.fakes

import io.github.kioba.domain.placeholder.user.model.Avatar
import io.github.kioba.domain.placeholder.user.model.Email
import io.github.kioba.domain.placeholder.user.model.Name
import io.github.kioba.domain.placeholder.user.model.User
import io.github.kioba.domain.placeholder.user.model.UserId
import io.github.kioba.domain.placeholder.user.model.UserName
import io.github.kioba.platform.test.TestDataScope

context(TestDataScope)
public fun givenUserId(): UserId =
  UserId(1)

context(TestDataScope)
public fun givenName(): Name =
  Name("John Doe")

context(TestDataScope)
public fun givenEmail(): Email =
  Email("JohnDoe@example.com")

context(TestDataScope)
public fun givenAvatar(): Avatar =
  Avatar("https://via.placeholder.com/150/56a8c2")


context(TestDataScope)
public fun givenUserName(): UserName =
  UserName("john_doe")

context(TestDataScope)
public fun givenUser(): User =
  User(
    id = givenUserId(),
    username = givenUserName(),
    name = givenName(),
    email = givenEmail(),
    avatar = givenAvatar(),
  )

context(TestDataScope)
public fun givenPostTitle(): String =
  "Lorem ipsum dolor sit amet"

context(TestDataScope)
public fun givenPostBody(): String =
  "Vivamus convallis massa nec nisl vehicula efficitur. " +
    "Mauris a semper nibh, sed pellentesque neque. " +
    "Proin nec consectetur magna. " +
    "Vivamus scelerisque lacus vitae quam porta imperdiet. " +
    "Nullam maximus a tortor sit amet finibus. Suspendisse in gravida purus. " +
    "Mauris eu diam a sapien consequat ullamcorper interdum non augue. " +
    "Donec turpis ex, fermentum dignissim faucibus ut, dictum sit amet justo."
