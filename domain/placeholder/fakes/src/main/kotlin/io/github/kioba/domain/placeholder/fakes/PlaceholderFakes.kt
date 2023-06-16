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


context(TestDataScope)
fun givenPost(): Post =
  Post(
    body = givenPostBody(),
    id = givenPostId().value,
    title = givenPostTitle(),
    userId = givenUserId().value,
  )

context(TestDataScope)
fun givenPostId(): PostId =
  PostId(1)

context(TestDataScope)
fun givenUserId(): UserId =
  UserId(1)

context(TestDataScope)
fun givenName(): Name =
  Name("John Doe")

context(TestDataScope)
fun givenEmail(): Email =
  Email("JohnDoe@example.com")

context(TestDataScope)
fun givenAvatar(): Avatar =
  Avatar("https://via.placeholder.com/150/56a8c2")


context(TestDataScope)
fun givenUserName(): UserName =
  UserName("john_doe")

context(TestDataScope)
fun givenUser(): User =
  User(
    id = givenUserId().value,
    username = givenUserName().value,
    name = givenName().value,
    email = givenEmail().value,
    avatar = givenAvatar().value,
  )

context(TestDataScope)
fun givenPostTitle(): String =
  "Lorem ipsum dolor sit amet"

context(TestDataScope)
fun givenPostBody(): String =
  "Vivamus convallis massa nec nisl vehicula efficitur. " +
    "Mauris a semper nibh, sed pellentesque neque. " +
    "Proin nec consectetur magna. " +
    "Vivamus scelerisque lacus vitae quam porta imperdiet. " +
    "Nullam maximus a tortor sit amet finibus. Suspendisse in gravida purus. " +
    "Mauris eu diam a sapien consequat ullamcorper interdum non augue. " +
    "Donec turpis ex, fermentum dignissim faucibus ut, dictum sit amet justo."
