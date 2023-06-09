package io.github.kioba.core

import dagger.Module
import dagger.Provides

@Module
class SchedulersModule {
  @Provides
  fun bindSchedulers(): ISchedulers = AppSchedulers()
}
