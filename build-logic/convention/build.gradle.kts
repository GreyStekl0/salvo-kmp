import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "io.github.stekl0.salvo.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradleApiPlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.ktlint.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("kmpApplication") {
            id =
                libs.plugins.salvo.kmp.application
                    .get()
                    .pluginId
            implementationClass = "KmpApplicationConventionPlugin"
        }
        register("kmpLibrary") {
            id =
                libs.plugins.salvo.kmp.library
                    .get()
                    .pluginId
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("lint") {
            id =
                libs.plugins.salvo.lint
                    .get()
                    .pluginId
            implementationClass = "LintConventionPlugin"
        }
    }
}
