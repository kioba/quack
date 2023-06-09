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
  ":feed",
  ":core",
  ":detail",
  ":placeholder",
)
