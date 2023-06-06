package io.github.kioba.placeholder

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.kioba.placeholder.persistence.PlaceholderDatabase
import io.github.kioba.placeholder.post.IPostModule
import io.github.kioba.placeholder.post.PostModule
import io.github.kioba.placeholder.user.IUserModule
import io.github.kioba.placeholder.user.UserModule

@Module(includes = [DatabaseModule::class])
interface PlaceholderSdkModule {
  @Binds
  fun bindUserModule(userModule: UserModule): IUserModule

  @Binds
  fun bindPostsModule(postModule: PostModule): IPostModule

  @Binds
  fun bindPlaceholderSdk(sdk: PlaceholderSdk): IPlaceholderSdk
}

@Module
class DatabaseModule {
  @Provides
  fun provideUserDao(context: Context) = PlaceholderDatabase.getInstance(context).userDao()

  @Provides
  fun providePostDao(context: Context) = PlaceholderDatabase.getInstance(context).postDao()

}
