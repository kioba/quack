package io.github.kioba.jsonplaceholder

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainModule {
  @ContributesAndroidInjector
  fun bindMainActivity(): MainActivity
}

