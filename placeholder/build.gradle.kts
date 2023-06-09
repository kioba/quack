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

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)

  implementation(libs.network.moshi.core)
  implementation(libs.network.moshi.adapters)
  implementation(libs.network.okhttp.core)
  implementation(libs.network.okhttp.loggingInterceptor)
  implementation(libs.network.okhttp.mockWebServer)
  implementation(libs.network.retrofit.core)
  implementation(libs.network.retrofit.moshiConverter)
  implementation(libs.network.retrofit.rxJavaAdapter)
  implementation(libs.network.retrofit.scalarsConverter)
  kapt(libs.network.moshi.kotlinCodeGen)

  Dependencies.persistence.forEach { implementation(it) }
  Dependencies.persistenceKapt.forEach { kapt(it) }
  Dependencies.persistenceTest.forEach { testImplementation(it) }
}
