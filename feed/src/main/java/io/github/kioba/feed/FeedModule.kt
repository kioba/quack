package io.github.kioba.feed

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.kioba.core.ViewModelKey

@Module(
  includes = [FeedModule.FeedProvider::class]
)
interface FeedModule {

  @ContributesAndroidInjector(modules = [(InjectViewModel::class)])
  fun bindFeedFragment(): FeedFragment

  @Module
  class InjectViewModel {

    @Provides
    fun provideFeatureViewModel(
      factory: ViewModelProvider.Factory,
      target: FeedFragment
    ): FeedViewModel =
      ViewModelProviders.of(
        target.activity as FragmentActivity,
        factory
      ).get(FeedViewModel::class.java)
  }

  @Module
  abstract class FeedProvider {

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun provideFeedViewModel(feedViewModel: FeedViewModel): ViewModel
  }

}
