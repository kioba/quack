package io.github.kioba.jsonplaceholder

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class PlaceholderApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent.builder()
      .application(this)
      .build()
      .inject(this)

  }

  override fun activityInjector(): AndroidInjector<Activity> = activityInjector

  override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

}
