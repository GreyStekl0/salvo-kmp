package io.github.stekl0.salvo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
