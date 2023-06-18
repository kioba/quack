import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
  kotlin("jvm")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_11.toString().toInt())
}

tasks.named("compileKotlin", KotlinCompilationTask::class.java) {
  compilerOptions {
    freeCompilerArgs.add("-Xcontext-receivers")
  }
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
  implementation(projects.platform.database)
  implementation(projects.platform.network)

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
  implementation(libs.arrow.coreData)
  implementation(libs.arrow.coreExtensions)
  implementation(libs.rx.java)
}