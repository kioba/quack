import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

dependencies {
  compileOnly(libs.build.android)
  compileOnly(libs.build.android.gradleApi)
  compileOnly(libs.build.kotlin)
}

tasks.withType<KotlinCompile>()
  .configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
    }
    sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }
  }
tasks.withType<JavaCompile>()
  .configureEach {
    sourceCompatibility = JavaVersion.VERSION_17.toString()
    targetCompatibility = JavaVersion.VERSION_17.toString()
  }
