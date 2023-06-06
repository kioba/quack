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

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }
}
