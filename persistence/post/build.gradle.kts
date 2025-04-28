plugins {
  kotlin("jvm")
  alias(libs.plugins.sqldelight)
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

sqldelight {
  databases {
    create("PostDB") {
      packageName = "dev.kioba.persistence"
    }
  }
}

kotlin {
  explicitApi()
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