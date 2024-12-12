plugins {
  kotlin("jvm")
  id("app.cash.sqldelight")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

kotlin {
  explicitApi()
}
sqldelight {
  databases {
    create("UserDB") {
      packageName = "io.github.kioba.persistence"
    }
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