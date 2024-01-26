plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  namespace = "io.github.kioba.platform.android.compose"
  compileSdk = 33

  buildFeatures {
    compose = true
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
    freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
  }
}

kotlin {
  jvmToolchain(11)
  explicitApi()
}

dependencies {
  implementation(platform(libs.androidX.compose.bom))
  implementation(libs.bundles.compose)
  implementation(libs.architecture.anchor)
  implementation(libs.androidX.fragment)
}