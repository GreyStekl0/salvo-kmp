package io.github.stekl0.salvo

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureDetekt() {
    extensions.configure<DetektExtension> {
        config.setFrom(rootProject.files("config/detekt/detekt.yml"))

        buildUponDefaultConfig = true
        allRules = false
        parallel = true

        dependencies.add("detektPlugins", libs.findLibrary("detekt.composeRules").get())
    }
}
