plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("kotlin-android-extensions")
}

android {
  setCompileSdkVersion(33)

  namespace = "io.github.kioba.placeholder"
}

dependencies {
  implementation(projects.core)

  implementation(libs.androidX.appcompat)
  implementation(libs.androidX.coreKtx)
  implementation(libs.androidX.constraintLayout)
  implementation(libs.dagger.core)
  implementation(libs.dagger.android)
  implementation(libs.dagger.androidSupport)
  implementation(libs.androidX.lifecycle.extensions)
  implementation(libs.androidX.lifecycle.viewModelKtx)
  implementation(libs.androidX.material)
  implementation(libs.picasso)
  implementation(libs.androidX.recyclerView)
  implementation(libs.rx.android)
  implementation(libs.rxbinding.lib)
  implementation(libs.rxbinding.appcompat)
  implementation(libs.rxbinding.core)
  implementation(libs.rxbinding.material)
  implementation(libs.rxbinding.recyclerview)
  implementation(libs.rx.java)
  implementation(libs.rx.relay)

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  testImplementation(libs.room.testing)

  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)

  implementation(libs.network.moshi.adapters)
  implementation(libs.network.moshi.core)
  implementation(libs.network.okhttp.core)
  implementation(libs.network.okhttp.loggingInterceptor)
  implementation(libs.network.okhttp.mockWebServer)
  implementation(libs.network.retrofit.core)
  implementation(libs.network.retrofit.moshiConverter)
  implementation(libs.network.retrofit.rxJavaAdapter)
  implementation(libs.network.retrofit.scalarsConverter)
  implementation(libs.room.runtime)
  implementation(libs.room.rxjava2)

  kapt(libs.androidX.lifecycle.compiler)
  kapt(libs.dagger.androidProcessor)
  kapt(libs.dagger.compiler)
  kapt(libs.network.moshi.kotlinCodeGen)
  kapt(libs.room.compiler)
}
