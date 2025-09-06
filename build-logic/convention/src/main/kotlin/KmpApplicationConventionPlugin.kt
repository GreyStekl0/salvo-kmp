import com.android.build.api.dsl.ApplicationExtension
import io.github.stekl0.salvo.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.application")
            pluginManager.apply("salvo.lint")

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget()

                jvmToolchain(17)

                listOf(
                    iosX64(),
                    iosArm64(),
                    iosSimulatorArm64(),
                ).forEach { iosTarget ->
                    iosTarget.binaries.framework {
                        baseName = "ComposeApp"
                        isStatic = true
                    }
                }

                sourceSets.all {
                    languageSettings.progressiveMode = true
                }
            }

            extensions.configure<ApplicationExtension> {
                namespace = "io.github.stekl0.salvo"
                compileSdk = 36
                defaultConfig {
                    applicationId = "io.github.stekl0.salvo"
                    minSdk = 24
                    targetSdk = 36
                }
                buildTypes {
                    release {
                        isMinifyEnabled = true
                    }
                    debug {
                        applicationIdSuffix = ".debug"
                        versionNameSuffix = "-DEBUG"
                        isDebuggable = true
                    }
                }
                packaging {
                    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                }
                compileOptions {
                    isCoreLibraryDesugaringEnabled = true
                }
                dependencies {
                    add("coreLibraryDesugaring", libs.findLibrary("desugarJdkLibs").get())
                }
            }
        }
    }
}
