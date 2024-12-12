import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

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
  implementation(libs.arrow.retrofit)
  implementation(libs.network.okhttp.loggingInterceptor)
  implementation(libs.network.retrofit.core)
  implementation(libs.network.retrofit.moshiConverter)
  implementation(libs.network.retrofit.scalarsConverter)
}