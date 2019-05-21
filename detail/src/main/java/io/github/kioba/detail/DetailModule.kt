package io.github.kioba.detail

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.kioba.core.IActionProcessor
import io.github.kioba.core.ViewModelKey
import io.github.kioba.detail.DetailModule.DetailProvider
import io.github.kioba.detail.mvi_models.DetailIntent
import io.github.kioba.detail.mvi_models.DetailResult

@Module(
  includes = [DetailProvider::class]
)
interface DetailModule {

  @ContributesAndroidInjector(modules = [(InjectViewModel::class)])
  fun bindDetailFragment(): DetailFragment

  @Module
  class InjectViewModel {

    @Provides
    fun provideFeatureViewModel(
      factory: ViewModelProvider.Factory,
      target: DetailFragment
    ): IDetailViewModel =
      ViewModelProviders.of(
        target.activity as FragmentActivity,
        factory
      ).get(DetailViewModel::class.java)
  }

  @Module
  interface DetailProvider {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun provideDetailViewModel(feedViewModel: DetailViewModel): ViewModel

    @Binds
    fun bindDetailActionProcessor(feedActionProcessor: DetailActionProcessor): IActionProcessor<DetailIntent, DetailResult>
  }

}
