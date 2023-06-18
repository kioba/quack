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
  ":core",
  ":designSystem",
  ":detail",
  ":domain:placeholder:core",
  ":domain:placeholder:fakes",
  ":feed",
  ":network:user",
  ":persistence:post",
  ":persistence:user",
  ":placeholder",
  ":platform:android-compose",
  ":platform:android-database",
  ":platform:database",
  ":platform:domain",
  ":platform:network",
  ":platform:test",
)
