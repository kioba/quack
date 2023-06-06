plugins {
  id("com.android.library")
  kotlin("android")
  kotlin("kapt")
  id("kotlin-android-extensions")
}

android {
  setCompileSdkVersion(33)

  namespace = "io.github.kioba.placeholder"
}

dependencies {
  implementation(project(":core"))

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }

  Dependencies.networking.forEach { implementation(it) }

  Dependencies.persistence.forEach { implementation(it) }
  Dependencies.persistenceKapt.forEach { kapt(it) }
  Dependencies.persistenceTest.forEach { testImplementation(it) }
}
