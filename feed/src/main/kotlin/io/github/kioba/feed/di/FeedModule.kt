package io.github.kioba.feed.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.kioba.feed.ui.FeedFragment

@Module
interface FeedModule {

  @ContributesAndroidInjector
  fun bindFeedFragment(): FeedFragment

}
