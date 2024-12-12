plugins {
  kotlin("jvm")
  alias(libs.plugins.ksp)
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
  implementation(projects.platform.network)

  implementation(libs.arrow.core)
  implementation(libs.network.moshi.core)
  implementation(libs.network.retrofit.core)

  ksp(libs.network.moshi.kotlinCodeGen)

}