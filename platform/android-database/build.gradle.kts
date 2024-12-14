plugins {
  kotlin("android")
  alias(libs.plugins.android.library)
  alias(libs.plugins.sqldelight)
}

android {
  namespace = "dev.kioba.platform.database"
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
  implementation(projects.platform.database)

  implementation(libs.database.driver.android)
}
