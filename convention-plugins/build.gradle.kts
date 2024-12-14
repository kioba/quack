import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  explicitApi()
}

tasks.withType<KotlinCompile>()
  .configureEach {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_17.toString()
    }
    sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }
  }


dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.android.tools.common)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)
}

tasks {
  validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
  }
}