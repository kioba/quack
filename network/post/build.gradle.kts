import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
  kotlin("jvm")
  kotlin("kapt")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

tasks.named("compileKotlin", KotlinCompilationTask::class.java) {
  compilerOptions {
    freeCompilerArgs.add("-Xcontext-receivers")
  }
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

  kapt(libs.network.moshi.kotlinCodeGen)

}