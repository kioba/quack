plugins {
  id("com.android.library")
  kotlin("android")
  id("com.squareup.sqldelight")
}

android {
  namespace = "io.github.kioba.platform.database"
  compileSdk = 33

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
    freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

}

kotlin {
  jvmToolchain(11)
  explicitApi()
}

dependencies {
  implementation(projects.platform.database)

  implementation(libs.database.android)
}