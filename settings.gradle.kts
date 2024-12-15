rootProject.name = "quack"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  includeBuild("convention-plugins")
  repositories {
    gradlePluginPortal()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
    mavenCentral()
  }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven {
      url = uri("https://maven.pkg.github.com/kioba/anchor")
      credentials {
        username = providers.gradleProperty("gpr.user").orNull ?: System.getenv("GITHUB_ACTOR")
        password = providers.gradleProperty("gpr.key").orNull ?: System.getenv("GH_PACKAGE_READ")
      }
    }
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
