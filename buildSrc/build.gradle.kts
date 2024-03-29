import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

dependencies {
  compileOnly(libs.build.android)
  compileOnly(libs.build.android.gradleApi)
  compileOnly(libs.build.kotlin)

  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks.withType<KotlinCompile>()
  .configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
      freeCompilerArgs = listOf("-Xcontext-receivers")
    }
    sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }
  }
tasks.withType<JavaCompile>()
  .configureEach {
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
  }
tasks.withType<KaptGenerateStubsTask>()
  .configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
  }
