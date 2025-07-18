plugins {
  kotlin("jvm")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation(projects.platform.database)
  implementation(projects.platform.network)

  implementation(libs.arrow.core)
  implementation(libs.jetbrains.kotlinx.coroutines.core)
}