package dev.kioba.domain.post.fakes

import dev.kioba.domain.placeholder.user.model.Email
import dev.kioba.domain.placeholder.user.model.Name
import dev.kioba.domain.post.api.model.CommentId
import dev.kioba.platform.test.TestDataScope

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentId(): CommentId =
  CommentId(value = 0L)

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentIdOne(): CommentId =
  CommentId(value = 1L)

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentIdTwo(): CommentId =
  CommentId(value = 2L)

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentUserEmail(): Email =
  Email(value = "test_comment_user@email.com")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentUserEmailOne(): Email =
  Email(value = "test_comment_user_one@email.com")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentUserEmailTwo(): Email =
  Email(value = "test_comment_user_two@email.com")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentUserName(): Name =
  Name("Sorrell Winmore")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentUserNameOne(): Name =
  Name("Terry Blade")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentUserNameTwo(): Name =
  Name("Jaquez bleakfire")

@Suppress("UnusedReceiverParameter")
public fun TestDataScope.givenCommentBody(): String =
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
    "Ut enim ad minim veniam, " +
    "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore" +
    " eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " +
    "sunt in culpa qui officia deserunt mollit anim id est laborum."

