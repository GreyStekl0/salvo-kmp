package io.github.stekl0.salvo

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

internal fun Project.configureKtlint() {
    extensions.configure<KtlintExtension> {
        version.set("1.7.1")

        reporters {
            reporter(ReporterType.CHECKSTYLE)
            reporter(ReporterType.SARIF)
        }

        filter {
            exclude("**/build/**")
            exclude("**/generated/**")
        }

        android.set(true)

        dependencies.add("ktlintRuleset", libs.findLibrary("ktlint.composeRules").get())

        outputToConsole.set(true)
    }
}
