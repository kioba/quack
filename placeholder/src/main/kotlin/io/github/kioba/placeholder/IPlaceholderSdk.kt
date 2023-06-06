package io.github.kioba.placeholder

import io.github.kioba.placeholder.network.model.Comment
import io.github.kioba.placeholder.post.Post
import io.github.kioba.placeholder.user.User
import io.reactivex.Flowable

interface IPlaceholderSdk {
  fun getFeed(): Flowable<List<Post>>
  fun getUsers(): Flowable<List<User>>
  fun getUser(userId: Int): Flowable<User>
  fun getPost(postId: Int): Flowable<Post>
  fun getComments(postId: Int): Flowable<List<Comment>>
}
