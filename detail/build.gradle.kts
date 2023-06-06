plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("kotlin-android-extensions")
}

android {
  setCompileSdkVersion(33)

  namespace = "io.github.kioba.detail"

}

dependencies {
  implementation(project(":core"))
  implementation(project(":placeholder"))

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }
}
