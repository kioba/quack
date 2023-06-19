plugins {
  id("com.android.application")
  kotlin("android")
}
android {
  namespace = "io.github.kioba.jsonplaceholder"

  setCompileSdkVersion(33)
  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = 33
    applicationId = "io.github.kioba.jsonplaceholder"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      @Suppress("UnstableApiUsage")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    getByName("debug") {
      enableUnitTestCoverage = true
    }
  }

  compileOptions.apply {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_11.toString()
  }

  sourceSets.map { it.java.srcDir("src/${it.name}/kotlin") }
}

kotlin {
  jvmToolchain(11)
}

dependencies {
  implementation(projects.feed)
  implementation(projects.platform.domain)
  implementation("androidx.appcompat:appcompat:1.6.1")
  coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")

  testImplementation(libs.test.mockK)
  testImplementation(libs.test.junit4)
  androidTestImplementation(libs.androidTest.androidXTest.core)
  androidTestImplementation(libs.androidTest.androidXTest.junit)
  androidTestImplementation(libs.androidTest.testRunner)
  androidTestImplementation(libs.androidTest.espresso)
}
