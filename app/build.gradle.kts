plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
  id("kotlin-android-extensions")
}
apply(from = "../buildSrc/android.kts")

android {
  defaultConfig {
    applicationId = "io.github.kioba.jsonplaceholder"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  implementation(project(":placeholder"))
  implementation(project(":feed"))
  implementation(project(":core"))

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }
}
