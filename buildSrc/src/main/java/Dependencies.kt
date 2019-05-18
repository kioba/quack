object Version {
  const val kotlin = "1.3.31"
  const val appcompat = "1.0.2"
  const val ktx = "1.0.2"
  const val constraintLayout = "1.1.3"
  const val material = "1.0.0"
  const val junit4 = "4.12"
  const val test_runner = "1.1.1"
  const val androidXTest = "1.1.0"
  const val espresso = "3.1.1"
}

object Dependencies {
  //   kotlin
  private const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

  //  AndroidX
  private const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
  private const val ktx = "androidx.core:core-ktx:${Version.ktx}"
  private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"

  //  material
  private const val material = "com.google.android.material:material:${Version.material}"

  //  unit test
  private const val junit4 = "junit:junit:${Version.junit4}"

  //  android test
  private const val androidXTestCore = "androidx.test:core:${Version.androidXTest}"
  private const val androidXTestJunit = "androidx.test.ext:junit:${Version.androidXTest}"
  private const val test_runner = "androidx.test:runner:${Version.test_runner}"
  private const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"

  val standard: List<String> = listOf(kotlin_stdlib, appcompat, ktx, constraintLayout, material)
  val test_standard: List<String> = listOf(junit4)
  val android_test_standard: List<String> = listOf(androidXTestCore, androidXTestJunit, test_runner, espresso)
}
