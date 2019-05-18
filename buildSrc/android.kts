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
  }
}
