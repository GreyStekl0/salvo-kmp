@file:Suppress("MaxLineLength", "ForbiddenComment")

import org.gradle.api.Plugin
import org.gradle.api.Project

class KmpApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.compose")
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            // TODO: I don’t know how to add a dependency of the form ‘compose.*’ (not from lib.version.toml).
            //  without them this plugin doesn’t make sense.
        }
    }
}
