import java.lang.System.getProperty

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
      authentication {
        create<BasicAuthentication>("basic")
      }
      credentials {
        username = getProperty("gpr.usr") ?: System.getenv("USERNAME")
        password = getProperty("gpr.key") ?: System.getenv("TOKEN")
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
