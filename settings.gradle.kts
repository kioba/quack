rootProject.name = "JsonPlaceholder"

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
  ":placeholder",
  ":platform:android-compose",
  ":platform:test",
)
