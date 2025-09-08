# Repository Guidelines

## Project Structure & Modules
- `composeApp/` — KMP application (Android + iOS Compose UI).
- `feature/*` — Feature libraries (e.g., `feature/home`).
- `iosApp/` — Xcode project and iOS host app.
- `build-logic/` — Gradle convention plugins (KMP, lint, Compose).
- `config/detekt/` — Detekt rules configuration.
- `gradle*`, `settings.gradle.kts`, `build.gradle.kts` — Build config.

## Build, Test, and Development
- Build all: `./gradlew build` (compiles, runs tests, and linters where wired).
- Verify/lint: `./gradlew check` (includes `detekt` and `ktlintCheck`).
- Kotlin style fix: `./gradlew ktlintFormat`.
- Android debug APK: `./gradlew :composeApp:assembleDebug`.
- Install on device: `./gradlew :composeApp:installDebug`.
- iOS: open `iosApp/iosApp.xcodeproj` in Xcode and Run. If frameworks are missing, run `./gradlew :composeApp:assemble` first, then build in Xcode.

## Coding Style & Naming
- Kotlin official style (`kotlin.code.style=official`), JDK 17 toolchain.
- Linting: ktlint 1.7.x and detekt 1.23.x via `build-logic` (“salvo.lint”).
- Compose rules enforced by detekt and ktlint; Composable/Test function naming exceptions are allowed (see `.editorconfig`).
- Packages: lowercase dot-separated; classes/objects: PascalCase; functions/props: camelCase; constants: UPPER_SNAKE_CASE.
- Module namespaces follow `io.github.stekl0.salvo.<area>`.

## Testing Guidelines
- Framework: `kotlin.test`.
- Location: `src/commonTest/kotlin` for shared tests; platform tests in `src/androidTest`/`src/iosTest` if needed.
- Naming: `SomethingTest.kt`, test functions start with `fun test…()` or use `@Test`.
- Run all tests: `./gradlew test` or `./gradlew check`. Module-only: `./gradlew :feature:home:check`.

## Commit & Pull Request Guidelines
- Use Conventional Commits: `feat: …`, `fix: …`, `chore: …`, etc.
- PRs must include: concise description, rationale, checklist, and linked issues (e.g., `Closes #123`).
- UI changes: attach screenshots or short clips.
- Keep changes scoped per module; update docs when altering build-logic or lint rules.

## Security & Tooling
- Secrets: pre-commit uses gitleaks. Enable locally: `pre-commit install`.
- Do not commit credentials, tokens, or generated build artifacts.
