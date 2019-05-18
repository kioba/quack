plugins {
  id("com.android.feature")
}

apply(from = "../buildSrc/android.kts")

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  Dependencies.standard.forEach { implementation(it) }
  Dependencies.test_standard.forEach { testImplementation(it) }
  Dependencies.android_test_standard.forEach { androidTestImplementation(it) }
}
