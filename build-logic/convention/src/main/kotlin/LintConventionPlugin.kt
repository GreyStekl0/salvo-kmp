import io.github.stekl0.salvo.configureDetekt
import io.github.stekl0.salvo.configureKtlint
import org.gradle.api.Plugin
import org.gradle.api.Project

class LintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")

            configureDetekt()
            configureKtlint()
        }
    }
}
