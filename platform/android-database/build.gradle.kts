plugins {
  id("com.android.library")
  kotlin("android")
  id("com.squareup.sqldelight")
}

android {
  namespace = "io.github.kioba.platform.database"
  compileSdk = 33

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
    freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
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
  implementation(projects.platform.database)

  implementation(libs.database.driver.android)
}
