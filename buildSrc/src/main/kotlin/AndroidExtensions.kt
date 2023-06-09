import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType

fun Project.configureCompose() {
  withAndroid {
    buildFeatures.compose = true

    composeOptions {
      kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
  }
}

fun Project.withAndroid(
  block: CommonExtension<*, *, *, *>.() -> Unit,
) {
  extensions.findByType<LibraryExtension>()
    ?: extensions.findByType<BaseAppModuleExtension>()
      null?.block()
}