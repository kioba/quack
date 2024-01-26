plugins {
  kotlin("jvm")
  id("com.squareup.sqldelight")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}
