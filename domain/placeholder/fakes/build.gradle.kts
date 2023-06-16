plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  setCompileSdkVersion(33)
  namespace = "io.github.kioba.domain.placeholder.fakes"

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
    freeCompilerArgs = freeCompilerArgs + ("-Xcontext-receivers")
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}


dependencies {
  implementation(projects.core)
  implementation(projects.placeholder)
  implementation(projects.platform.test)
}
