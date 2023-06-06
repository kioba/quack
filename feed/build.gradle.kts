plugins {
  id("com.android.library")
  kotlin("android")
kotlin("kapt")
  id("kotlin-android-extensions")
}
android {
  setCompileSdkVersion(33)

  namespace = "io.github.kioba.feed"

}

dependencies {
  implementation(project(":core"))
  implementation(project(":placeholder"))
  implementation(project(":detail"))

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }
}
