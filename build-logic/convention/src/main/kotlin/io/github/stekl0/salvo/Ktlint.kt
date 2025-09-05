package io.github.stekl0.salvo

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension

internal fun Project.configureKtlint() {
    extensions.configure<KtlintExtension> {
        filter {
            exclude("**/build/**")
        }

        dependencies.add("ktlintRuleset", libs.findLibrary("ktlint.composeRules").get())
    }
}
