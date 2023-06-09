plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
}

android {
  setCompileSdkVersion(33)
  namespace = "io.github.kioba.core"
}

dependencies {
  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
