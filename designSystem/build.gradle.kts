import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id("com.android.library")
  alias(libs.plugins.jetbrainsCompose)
  alias(libs.plugins.composeCompiler)

}

android {
  namespace = "dev.kioba.design.system"
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
  implementation(platform(libs.androidX.compose.bom))
  implementation(libs.bundles.compose)

  implementation(libs.androidX.compose.coil)
  implementation(libs.androidX.compose.accompanist)

  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
