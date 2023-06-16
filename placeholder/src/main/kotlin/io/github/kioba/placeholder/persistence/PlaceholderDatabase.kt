package io.github.kioba.placeholder.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.kioba.placeholder.post.DatabasePost
import io.github.kioba.placeholder.post.PostDao
import io.github.kioba.placeholder.user.DatabaseUser
import io.github.kioba.placeholder.user.UserDao

@Database(entities = [DatabaseUser::class, DatabasePost::class], version = 1)
internal abstract class PlaceholderDatabase : RoomDatabase(), DaoScope {
  companion object {

    @Volatile
    private var INSTANCE: PlaceholderDatabase? = null

    fun getInstance(context: Context): PlaceholderDatabase =
      INSTANCE ?: synchronized(this) {
        INSTANCE
          ?: buildDatabase(
            context
          ).also { INSTANCE = it }
      }

    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(
        context.applicationContext,
        PlaceholderDatabase::class.java,
        "placeholder.db"
      )
        .build()
  }
}

interface DaoScope {
  fun userDao(): UserDao

  fun postDao(): PostDao

}
