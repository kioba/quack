plugins {
  id("com.android.library")
  kotlin("android")
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
}

android {
  namespace = "dev.kioba.platform.android.compose"
  compileSdk = libs.versions.compileSdk.get().toInt()

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

}

kotlin {
  explicitApi()
}

dependencies {
  implementation(libs.androidX.fragment)
  implementation(libs.architecture.anchor)
  implementation(libs.bundles.compose)
  implementation(libs.navigation.compose)
  implementation(platform(libs.androidX.compose.bom))
}
