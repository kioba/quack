package io.github.kioba.core

import dagger.Binds
import dagger.Module

@Module
interface SchedulersModule {
  @Binds
  fun bindSchedulers(schedulers: AppSchedulers): ISchedulers
}
