import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
  kotlin("jvm")
  id("com.squareup.sqldelight")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

sqldelight {
  database("PostDB") {
    packageName = "io.github.kioba.persistence"
  }
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
  implementation(projects.platform.database)

  implementation(libs.database.driver.jvm)
  implementation(libs.database.coroutines)
}