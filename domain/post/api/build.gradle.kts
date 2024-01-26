import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
  kotlin("jvm")
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
  implementation(projects.domain.user.api)
  implementation(projects.network.post)
  implementation(projects.persistence.post)
  implementation(projects.platform.database)
  implementation(projects.platform.domain)
  implementation(projects.platform.network)

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
  implementation(libs.arrow.core)
}