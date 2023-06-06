plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("kapt")
  id("kotlin-android-extensions")
}
android {
  namespace = "io.github.kioba.jsonplaceholder"

  setCompileSdkVersion(33)
  defaultConfig {
    minSdk = AndroidVersions.minSDK
    targetSdk = 33
    applicationId = "io.github.kioba.jsonplaceholder"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    getByName("debug") {
      isTestCoverageEnabled = true
    }
  }

  compileOptions.apply {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  sourceSets {
    map { it.java.srcDir("src/${it.name}/kotlin") }
  }
}

dependencies {
  implementation(project(":placeholder"))
  implementation(project(":feed"))
  implementation(project(":detail"))
  implementation(project(":core"))
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.standardKapt.forEach { kapt(it) }

  Dependencies.testStandard.forEach { testImplementation(it) }
  Dependencies.testAndroidStandard.forEach { androidTestImplementation(it) }
}
