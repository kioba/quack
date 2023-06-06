buildscript {
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath(libs.build.android)
    classpath(libs.build.kotlin)
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }
}

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}