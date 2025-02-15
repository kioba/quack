plugins {
  id("com.android.application")
  kotlin("android")
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
}
android {
  namespace = "dev.kioba.quack"

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    compileSdk = libs.versions.compileSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    applicationId = "dev.kioba.quack"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
    }

    getByName("debug") {
      enableUnitTestCoverage = true
    }
  }

  compileOptions.apply {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }
}

kotlin {
  explicitApi()
}

dependencies {
  implementation(libs.foundation.layout.android)
  coreLibraryDesugaring(libs.desugar.jdk.libs)

  implementation(libs.androidX.activity)
  implementation(libs.androidX.appcompat)
  implementation(libs.androidX.compose.activity)
  implementation(projects.feature.feed)
  implementation(projects.platform.domain)
  implementation(libs.navigation.compose)

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)

  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
