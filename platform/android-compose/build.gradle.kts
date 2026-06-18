import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.library")
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)
}

android {
  namespace = "dev.kioba.platform.android.compose"
  compileSdk = libs.versions.compileSdk.get().toInt()

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

}

kotlin {
  explicitApi()
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  implementation(libs.androidX.fragment)
  implementation(libs.architecture.anchor)
  implementation(libs.bundles.compose)
  implementation(libs.navigation.compose)
  implementation(platform(libs.androidX.compose.bom))
}
