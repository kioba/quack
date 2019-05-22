package io.github.kioba.placeholder.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface UserDao {

  /**
   * Get a user by id.
   * @return the user from the table with a specific id.
   */
  @Query("SELECT * FROM Users WHERE userid = :id")
  fun getUserById(id: Int): Flowable<DatabaseUser>

  /**
   * Get all the users.
   * @return the users from the table.
   */
  @Query("SELECT * FROM Users")
  fun getUsers(): Flowable<List<DatabaseUser>>

  /**
   * Get a user by id.
   * @return the user from the table with a specific id.
   */
  @Query("SELECT * FROM Users WHERE userid = :id")
  fun getUserByIdMaybe(id: Int): Maybe<DatabaseUser>

  /**
   * Get all the users.
   * @return the users from the table.
   */
  @Query("SELECT * FROM Users")
  fun getUsersMaybe(): Maybe<List<DatabaseUser>>

  /**
   * Insert a user in the database. If the user already exists, replace it.
   * @param user the user to be inserted.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertUser(user: DatabaseUser): Completable

  /**
   * Insert users in the database. If the user already exists, replace it.
   * @param users the users to be inserted.
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertUser(users: List<DatabaseUser>): Completable

  /**
   * Delete all users.
   */
  @Query("DELETE FROM Users")
  fun deleteAllUsers()

}
