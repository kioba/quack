plugins {
  id("com.android.library")
  kotlin("android")
  id("app.cash.sqldelight")
}

android {
  namespace = "dev.kioba.platform.database"
  compileSdk = 33

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
  implementation(projects.platform.database)

  implementation(libs.database.driver.android)
}
