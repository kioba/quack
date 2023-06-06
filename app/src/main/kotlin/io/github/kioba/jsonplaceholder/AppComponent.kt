package io.github.kioba.jsonplaceholder

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.github.kioba.core.SchedulersModule
import io.github.kioba.placeholder.PlaceholderSdkModule
import javax.inject.Singleton

@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    PlaceholderSdkModule::class,
    SchedulersModule::class,
    UIModule::class
  ]
)
@Singleton
interface AppComponent {

  fun inject(app: PlaceholderApplication)

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Context): Builder

    fun build(): AppComponent
  }
}
