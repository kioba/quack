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
  implementation(projects.network.user)
  implementation(projects.persistence.user)
  implementation(projects.platform.database)
  implementation(projects.platform.domain)
  implementation(projects.platform.network)

  implementation(libs.jetbrains.kotlinx.coroutines.core)
  implementation(libs.arrow.core)
}