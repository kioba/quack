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
  ":domain:placeholder:fakes",
  ":feed",
  ":persistence:post",
  ":placeholder",
  ":platform:android-compose",
  ":platform:database",
  ":platform:network",
  ":platform:test",
)
include(":platform:android-database")
include(":persistence:user")
include(":domain:placeholder:core")
