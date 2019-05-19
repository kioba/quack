package io.github.kioba.placeholder

import io.github.kioba.placeholder.json_placeholder.network_models.Comment
import io.github.kioba.placeholder.json_placeholder.network_models.Post
import io.github.kioba.placeholder.json_placeholder.network_models.User
import io.reactivex.Flowable

interface IPlaceholderSdk {
  fun getFeed(): Flowable<List<Post>>
  fun getUsers(): Flowable<List<User>>
  fun getUser(userId: Int): Flowable<User>
  fun getPost(postId: Int): Flowable<Post>
  fun getComments(postId: Int): Flowable<List<Comment>>
}
