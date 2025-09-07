package io.github.stekl0.salvo

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

internal fun Project.configureDetekt() {
    extensions.configure<DetektExtension> {
        toolVersion = "1.23.8"

        source.setFrom(
            "src/commonMain/kotlin",
            "src/androidMain/kotlin",
            "src/iosMain/kotlin",
        )

        buildUponDefaultConfig = true
        allRules = false
        parallel = true

        config.setFrom(rootProject.files("config/detekt/detekt.yml"))

        dependencies.add("detektPlugins", libs.findLibrary("detekt.composeRules").get())
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            sarif.required.set(true)
            sarif.outputLocation.set(file("build/reports/detekt/$name.sarif"))
        }
    }
}
