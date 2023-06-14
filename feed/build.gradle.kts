plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  namespace = "io.github.kioba.feed"
  setCompileSdkVersion(33)

  buildFeatures {
    compose = true
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
    freeCompilerArgs = listOf("-Xcontext-receivers")
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
  }
}

kotlin {
  jvmToolchain(11)
}

dependencies {
  implementation(projects.core)
  implementation(projects.designSystem)
  implementation(projects.detail)
  implementation(projects.domain.placeholder.fakes)
  implementation(projects.placeholder)
  implementation(projects.platform.androidCompose)
  implementation(projects.platform.test)

  implementation(platform(libs.androidX.compose.bom))
  implementation(libs.bundles.compose)

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

  implementation(libs.arrow.coreData)
  implementation(libs.arrow.coreExtensions)
  implementation(libs.arrow.effectsData)
  implementation(libs.arrow.effectsExtensions)
  implementation(libs.arrow.extrasData)
  implementation(libs.arrow.extrasExtensions)
  implementation(libs.arrow.syntax)
  implementation(libs.arrow.typeclasses)

  implementation(libs.architecture.anchor)
  implementation(libs.kotlinX.coroutines.rx2)
  implementation(libs.kotlinX.coroutines.core)
  implementation(libs.kotlinX.coroutines.android)

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
