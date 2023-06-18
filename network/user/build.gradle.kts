import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
  kotlin("jvm")
  kotlin("kapt")
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

kotlin {
  jvmToolchain(11)
  explicitApi()
}

dependencies {
  implementation(projects.platform.network)

  implementation(libs.arrow.coreData)
  implementation(libs.arrow.coreExtensions)
  implementation(libs.network.moshi.core)
  implementation(libs.network.retrofit.core)

  kapt(libs.network.moshi.kotlinCodeGen)

}