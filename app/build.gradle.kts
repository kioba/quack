plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
}
android {
  namespace = "io.github.kioba.jsonplaceholder"

  setCompileSdkVersion(33)
  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = 33
    applicationId = "io.github.kioba.jsonplaceholder"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      @Suppress("UnstableApiUsage")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    getByName("debug") {
      enableUnitTestCoverage = true
    }
  }

  compileOptions.apply {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
  }

  sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }
}

kotlin {
  jvmToolchain(11)
}

dependencies {
  implementation(projects.placeholder)
  implementation(projects.feed)
  implementation(projects.detail)
  implementation(projects.core)
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

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
