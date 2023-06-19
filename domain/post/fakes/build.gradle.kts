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
  implementation(projects.domain.post.api)
  implementation(projects.domain.user.api)
  implementation(projects.domain.user.fakes)
  implementation(projects.platform.test)
}
