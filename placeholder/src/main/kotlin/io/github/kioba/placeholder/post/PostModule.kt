package io.github.kioba.placeholder.post

import io.github.kioba.core.ISchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class PostModule @Inject constructor(
  appSchedulers: ISchedulers,
  private val postDao: PostDao,
) {

  val io = appSchedulers.io
  fun getPost(postId: Int): Maybe<DatabasePost> =
    postDao.getPostByIdMaybe(postId).subscribeOn(io)

  fun getPosts(): Maybe<List<DatabasePost>> = postDao.getPostMaybe().subscribeOn(io)

  fun insertPost(post: DatabasePost): Completable =
    postDao.insertPost(post).subscribeOn(io)

  fun insertPosts(posts: List<DatabasePost>) = postDao.insertPost(posts).subscribeOn(io)

  fun postStream(postId: Int): Flowable<DatabasePost> =
    postDao.getPostById(postId).subscribeOn(io)

  fun postsStream(): Flowable<List<DatabasePost>> = postDao.getPost().subscribeOn(io)
}
