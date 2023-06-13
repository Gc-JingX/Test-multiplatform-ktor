package com.johnny.basemodule

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform