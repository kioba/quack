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
  implementation(projects.core)
  implementation(projects.placeholder)
  implementation(projects.detail)

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }
}
