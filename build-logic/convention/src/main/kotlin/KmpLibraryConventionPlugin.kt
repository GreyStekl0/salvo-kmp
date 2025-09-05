import com.android.build.api.dsl.androidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.multiplatform")
            pluginManager.apply("com.android.kotlin.multiplatform.library")

            extensions.configure<KotlinMultiplatformExtension> {
                androidLibrary {
                    compileSdk = 36
                    minSdk = 24
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
                }
                iosX64()
                iosArm64()
                iosSimulatorArm64()

                explicitApi()

                sourceSets.all {
                    languageSettings.progressiveMode = true
                }
            }
        }
    }
}
