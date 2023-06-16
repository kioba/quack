package io.github.kioba.placeholder

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.kioba.placeholder.persistence.DaoScope
import io.github.kioba.placeholder.persistence.PlaceholderDatabase
import io.github.kioba.placeholder.post.PostDao
import io.github.kioba.placeholder.user.UserDao

@Module(includes = [DatabaseModule::class])
interface PlaceholderSdkModule

@Module
class DatabaseModule {
  @Provides
  fun provideDaoScope(context: Context): DaoScope =
    PlaceholderDatabase.getInstance(context)

  @Provides
  fun provideUserDao(context: Context): UserDao =
    PlaceholderDatabase.getInstance(context).userDao()

  @Provides
  fun providePostDao(context: Context): PostDao =
    PlaceholderDatabase.getInstance(context).postDao()

}
