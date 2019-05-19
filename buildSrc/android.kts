android {
  compileSdkVersion(AndroidVersions.targetSDK)
  defaultConfig {
    minSdkVersion(AndroidVersions.minSDK)
    targetSdkVersion(AndroidVersions.targetSDK)
    versionCode = AndroidVersions.versionCode
    versionName = AndroidVersions.versionName
  }
  buildTypes {
    getByName("release") {
      minifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }

    getByName("debug") {
      testCoverageEnabled = true
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }


  testOptions {
    unitTests {
      all {
        testLogging {
          events("passed", "skipped", "failed")
        }
      }
    }
  }

}
