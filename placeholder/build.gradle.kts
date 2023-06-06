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

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }

  implementation(libs.networking.moshi.core)
  implementation(libs.networking.moshi.adapters)
  implementation(libs.networking.okhttp.core)
  implementation(libs.networking.okhttp.loggingInterceptor)
  implementation(libs.networking.okhttp.mockWebServer)
  implementation(libs.networking.retrofit.core)
  implementation(libs.networking.retrofit.moshiConverter)
  implementation(libs.networking.retrofit.rxJavaAdapter)
  implementation(libs.networking.retrofit.scalarsConverter)
  kapt(libs.networking.moshi.kotlinCodeGen)

  Dependencies.persistence.forEach { implementation(it) }
  Dependencies.persistenceKapt.forEach { kapt(it) }
  Dependencies.persistenceTest.forEach { testImplementation(it) }
}
