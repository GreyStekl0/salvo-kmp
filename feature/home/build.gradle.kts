plugins {
    alias(libs.plugins.salvo.kmp.library)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidLibrary {
        namespace = "io.github.stekl0.salvo.feature.home"
    }
}
