rootProject.name = "Quack"

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
  ":app",
  ":designSystem",
  ":domain:post:api",
  ":domain:post:fakes",
  ":domain:user:api",
  ":domain:user:fakes",
  ":feature:feed",
  ":network:post",
  ":network:user",
  ":persistence:post",
  ":persistence:user",
  ":platform:android-compose",
  ":platform:android-database",
  ":platform:database",
  ":platform:domain",
  ":platform:network",
  ":platform:test",
)
