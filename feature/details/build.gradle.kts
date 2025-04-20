
plugins {
  alias(libs.plugins.android.library)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.screenshot)
  alias(libs.plugins.serialization)
}

android {
  namespace = "dev.kioba.feature.details"

  compileSdk = libs.versions.compileSdk
    .get()
    .toInt()

  defaultConfig {
    minSdk = 21

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
      imageDifferenceThreshold = 0.05f // 5%
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
  implementation(projects.platform.androidCompose)
  implementation(projects.platform.androidDatabase)
  implementation(projects.platform.database)
  implementation(projects.platform.domain)
  implementation(projects.platform.network)
  implementation(projects.platform.test)
//  implementation(projects.domain.user.api)
//  implementation(projects.domain.user.fakes)

  implementation(libs.bundles.compose)
  implementation(libs.navigation.compose)
  implementation(platform(libs.androidX.compose.bom))

  implementation(libs.arrow.core)

  implementation(libs.architecture.anchor)
  implementation(libs.kotlinX.coroutines.core)
  implementation(libs.kotlinX.coroutines.android)
  implementation(libs.kotlinx.serialization.json)

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  testImplementation(libs.architecture.anchorTest)
  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
