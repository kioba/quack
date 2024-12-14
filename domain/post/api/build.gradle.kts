plugins {
  kotlin("jvm")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

dependencies {
  implementation(projects.domain.user.api)
  implementation(projects.network.post)
  implementation(projects.persistence.post)
  implementation(projects.platform.database)
  implementation(projects.platform.domain)
  implementation(projects.platform.network)

  implementation(libs.jetbrains.kotlinx.coroutines.core)
  implementation(libs.arrow.core)
}