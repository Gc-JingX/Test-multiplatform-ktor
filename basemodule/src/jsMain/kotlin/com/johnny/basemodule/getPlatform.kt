package com.johnny.basemodule

class JsPlatform : Platform {
    override val name: String = "js  "
}
actual fun getPlatform(): Platform =JsPlatform()