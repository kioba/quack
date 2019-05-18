object Version {
  const val kotlin = "1.3.31"
  const val junit4 = "4.12"
  const val test_runner = "1.1.1"
  const val androidXTest = "1.1.0"
  const val espresso = "3.1.1"

  // androidX
  const val androidXAppcompat = "1.1.0-alpha05"
  const val coreKtx = "1.0.2"

  // AndroidX material version
  const val material = "1.0.0"

  // AndroidX navigation version
  const val androidXNavigation = "1.0.0-alpha05"

  // androidX recyclerview version
  const val androidXRecyclerview = "1.1.0-alpha05"

  // androidX constraintLayout version
  const val constraintLayout = "1.1.3"
  const val androidXConstraintLayout = "2.0.0-beta1"

  // androidX
  const val androidXLifecycle = "2.0.0"

}

object Dependencies {
  //   kotlin
  private const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

  //  AndroidX
  private const val appcompat = "androidx.appcompat:appcompat:${Version.androidXAppcompat}"
  private const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"

  // androidX recyclerView
  private const val recyclerView = "androidx.recyclerview:recyclerview:${Version.androidXRecyclerview}"

  // androidX constraintLayout
  private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.androidXConstraintLayout}"

  // androidX Architecture components
  private const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.androidXLifecycle}"
  private const val lifecycleViewModelKtx =
    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.androidXLifecycle}" // use -ktx for Kotlin
  private const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.androidXLifecycle}"

  //  material
  private const val material = "com.google.android.material:material:${Version.material}"

  //  unit test
  private const val junit4 = "junit:junit:${Version.junit4}"

  //  android test
  private const val androidXTestCore = "androidx.test:core:${Version.androidXTest}"
  private const val androidXTestJunit = "androidx.test.ext:junit:${Version.androidXTest}"
  private const val test_runner = "androidx.test:runner:${Version.test_runner}"
  private const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"

  val standard_kapt: List<String> = listOf(lifecycleCompiler)
  val standard: List<String> = listOf(
    kotlin_stdlib,
    appcompat,
    coreKtx,
    constraintLayout,
    material,
    lifecycleViewModelKtx,
    lifecycleExtensions,
    recyclerView
  )
  val test_standard: List<String> = listOf(junit4)
  val android_test_standard: List<String> = listOf(androidXTestCore, androidXTestJunit, test_runner, espresso)
}
