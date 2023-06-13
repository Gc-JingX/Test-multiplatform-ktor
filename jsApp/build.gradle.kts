plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
        // 设置入口文件
    }
    sourceSets {
        val jsMain by getting  {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

