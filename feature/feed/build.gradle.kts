plugins {
  id("com.android.library")
  kotlin("android")
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.screenshot)
}

android {
  namespace = "dev.kioba.feature.feed"
  compileSdk = libs.versions.compileSdk
    .get()
    .toInt()

  defaultConfig {
    minSdk = 21
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
  @Suppress("UnstableApiUsage")
  experimentalProperties["android.experimental.enableScreenshotTest"] = true

  @Suppress("UnstableApiUsage")
  testOptions {
    screenshotTests {
      imageDifferenceThreshold = 0.03f // 1%
    }

  }
}

kotlin {
  explicitApi()
}

dependencies {
  implementation(projects.designSystem)
  implementation(projects.domain.post.api)
  implementation(projects.domain.post.fakes)
  implementation(projects.domain.user.api)
  implementation(projects.domain.user.fakes)
  implementation(projects.platform.androidCompose)
  implementation(projects.platform.androidDatabase)
  implementation(projects.platform.database)
  implementation(projects.platform.domain)
  implementation(projects.platform.network)
  implementation(projects.platform.test)

  implementation(platform(libs.androidX.compose.bom))
  implementation(libs.bundles.compose)
  implementation(libs.androidX.fragment)

  implementation(libs.arrow.core)

  implementation(libs.architecture.anchor)
  implementation(libs.kotlinX.coroutines.core)
  implementation(libs.kotlinX.coroutines.android)

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  testImplementation(libs.architecture.anchorTest)
  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
