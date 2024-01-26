plugins {
  id("com.android.application")
  kotlin("android")
}
android {
  namespace = "io.github.kioba.quack"

  setCompileSdkVersion(34)
  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = 34
    applicationId = "io.github.kioba.quack"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
    }

    getByName("debug") {
      enableUnitTestCoverage = true
    }
  }

  compileOptions.apply {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }

  sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }
}

kotlin {
  jvmToolchain(11)
}

dependencies {
  implementation(projects.feature.feed)
  implementation(projects.platform.domain)

  implementation(libs.androidX.appcompat)

  coreLibraryDesugaring(libs.desugar.jdk.libs)

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
