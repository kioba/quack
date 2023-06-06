package io.github.kioba.placeholder.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface PostDao {

  /**
   * Get a post by id.
   * @return the post from the table with a specific id.
   */
  @Query("SELECT * FROM Posts WHERE postid = :id")
  fun getPostById(id: Int): Flowable<DatabasePost>

  /**
   * Get all the posts.
   * @return the posts from the table.
   */
  @Query("SELECT * FROM Posts")
  fun getPost(): Flowable<List<DatabasePost>>

  /**
   * Get a post by id.
   * @return the post from the table with a specific id.
   */
  @Query("SELECT * FROM Posts WHERE postid = :id")
  fun getPostByIdMaybe(id: Int): Maybe<DatabasePost>

  /**
   * Get all the posts.
   * @return the posts from the table.
   */
  @Query("SELECT * FROM Posts")
  fun getPostMaybe(): Maybe<List<DatabasePost>>

  /**
   * Insert a post in the database. If the post already exists, replace it.
   * @param post the post to be inserted.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPost(post: DatabasePost): Completable

  /**
   * Insert posts in the database. If the post already exists, replace it.
   * @param posts the posts to be inserted.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertPost(posts: List<DatabasePost>): Completable

  /**
   * Delete all posts.
   */
  @Query("DELETE FROM Posts")
  fun deleteAllPosts()

}
