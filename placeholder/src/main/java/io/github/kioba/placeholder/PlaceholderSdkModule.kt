package io.github.kioba.placeholder

import dagger.Binds
import dagger.Module

@Module
interface PlaceholderSdkModule {
  @Binds
  fun bindPlaceholderSdk(sdk: PlaceholderSdk): IPlaceholderSdk
}
