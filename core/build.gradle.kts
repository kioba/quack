plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  setCompileSdkVersion(33)
  namespace = "io.github.kioba.core"

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }

}


kotlin {
  jvmToolchain(11)
}

tasks.withType<org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask>()
  .configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
  }

dependencies {

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

  kapt(libs.androidX.lifecycle.compiler)
  kapt(libs.dagger.compiler)
  kapt(libs.dagger.androidProcessor)

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
