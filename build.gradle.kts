plugins {
  alias(libs.plugins.jetbrainsCompose) apply false
  alias(libs.plugins.composeCompiler) apply false
  alias(libs.plugins.ksp) apply false
}

buildscript {
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath(libs.build.android)
    classpath(libs.build.kotlin)

    classpath(libs.database.gradlePlugin)
  }
}


allprojects {
  repositories {
    google()
    mavenCentral()
    maven {
      url = uri("https://maven.pkg.github.com/kioba/anchor")
      authentication {
        create<BasicAuthentication>("basic")
      }
      credentials {
        username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
        password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
      }
    }

  }
}

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}