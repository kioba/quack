plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}
apply(from = "../buildSrc/android.kts")

android {
    defaultConfig {
        applicationId = "io.github.kioba.jsonplaceholder"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    Dependencies.standard.forEach { implementation(it) }
    Dependencies.standard_kapt.forEach { kapt(it) }

    Dependencies.test_standard.forEach { testImplementation(it) }
    Dependencies.android_test_standard.forEach { androidTestImplementation(it) }
}
