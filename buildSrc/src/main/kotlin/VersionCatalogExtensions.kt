import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.accessors.dm.LibrariesForLibs

internal val Project.libs: LibrariesForLibs
  get() = (rootProject as ExtensionAware).extensions.getByName("libs") as LibrariesForLibs
