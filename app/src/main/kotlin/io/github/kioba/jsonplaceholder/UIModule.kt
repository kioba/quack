package io.github.kioba.jsonplaceholder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.github.kioba.detail.DetailModule
import io.github.kioba.feed.di.FeedModule
import javax.inject.Provider
import javax.inject.Singleton

@Module(
  includes = [
    MainModule::class,
    FeedModule::class,
    DetailModule::class
  ]
)
class UIModule {
  /**
   * Singleton factory that searches generated map for specific provider
   * and uses it to get a ViewModel instance
   */
  @Provides
  @Singleton
  fun provideViewModelFactory(
    providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
  ): ViewModelProvider.Factory = AppViewModelFactory(providers)
}
