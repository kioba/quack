import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask

plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  setCompileSdkVersion(33)
  namespace = "io.github.kioba.placeholder"

  kotlin {
    explicitApi()
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
    freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

tasks.withType<KaptGenerateStubsTask>()
  .configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
  }


dependencies {
  implementation(projects.core)
  implementation(projects.domain.placeholder.core)
  implementation(projects.network.user)
  implementation(projects.persistence.post)
  implementation(projects.persistence.user)
  implementation(projects.platform.database)
  implementation(projects.platform.domain)
  implementation(projects.platform.network)


  implementation(libs.dagger.core)
  implementation(libs.dagger.android)
  implementation(libs.dagger.androidSupport)

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

  implementation(libs.arrow.coreData)
  implementation(libs.arrow.coreExtensions)

  implementation(libs.network.moshi.adapters)
  implementation(libs.network.moshi.core)
  implementation(libs.network.okhttp.core)
  implementation(libs.network.okhttp.loggingInterceptor)
  implementation(libs.network.okhttp.mockWebServer)
  implementation(libs.network.retrofit.core)
  implementation(libs.room.runtime)
  implementation(libs.room.rxjava2)

  kapt(libs.androidX.lifecycle.compiler)
  kapt(libs.dagger.androidProcessor)
  kapt(libs.dagger.compiler)
  kapt(libs.network.moshi.kotlinCodeGen)
  kapt(libs.room.compiler)
}
