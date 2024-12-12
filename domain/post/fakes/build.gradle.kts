plugins {
  kotlin("jvm")
}

kotlin {
  explicitApi()
  jvmToolchain(JavaVersion.VERSION_17.toString().toInt())
}

java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
  implementation(projects.domain.post.api)
  implementation(projects.domain.user.api)
  implementation(projects.domain.user.fakes)
  implementation(projects.platform.test)
}
