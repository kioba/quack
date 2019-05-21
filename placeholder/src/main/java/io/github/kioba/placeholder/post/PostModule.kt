package io.github.kioba.placeholder.post

import io.github.kioba.core.ISchedulers
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject


interface IPostModule {
  fun insertPost(post: DatabasePost): Completable
  fun insertPosts(posts: List<DatabasePost>): Completable
  fun postStream(postId: Int): Flowable<DatabasePost>
  fun postsStream(): Flowable<List<DatabasePost>>
  fun getPost(postId: Int): Maybe<DatabasePost>
  fun getPosts(): Maybe<List<DatabasePost>>
}

class PostModule @Inject constructor(
  appSchedulers: ISchedulers,
  private val postDao: PostDao
) : IPostModule {

  val io = appSchedulers.io
  override fun getPost(postId: Int): Maybe<DatabasePost> =
    postDao.getPostByIdMaybe(postId).subscribeOn(io)

  override fun getPosts(): Maybe<List<DatabasePost>> = postDao.getPostMaybe().subscribeOn(io)

  override fun insertPost(post: DatabasePost): Completable =
    postDao.insertPost(post).subscribeOn(io)

  override fun insertPosts(posts: List<DatabasePost>) = postDao.insertPost(posts).subscribeOn(io)

  override fun postStream(postId: Int): Flowable<DatabasePost> =
    postDao.getPostById(postId).subscribeOn(io)

  override fun postsStream(): Flowable<List<DatabasePost>> = postDao.getPost().subscribeOn(io)
}
