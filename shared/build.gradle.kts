@file:Suppress("OPT_IN_IS_NOT_ENABLED")

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp") version "1.8.21-1.0.11"
    id("de.jensklingenberg.ktorfit") version "1.0.0"
    id("kotlinx-serialization")

}

version = "1.0-SNAPSHOT"
val ktorVersion = "2.3.1"
val ktorfitVersion = "1.4.1"

configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
    version = ktorfitVersion
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}
kotlin {
    android()

    jvm("desktop")

    ios()
    iosSimulatorArm64()

    js(IR) {
        browser()
        this.nodejs()
        binaries.executable()
    }

    macosX64 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }
    macosArm64 {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    cocoapods {
        summary = "Shared code for the sample"
        homepage = "https://github.com/JetBrains/compose-jb"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

//                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
//                implementation("io.ktor:ktor-client-core:$ktorVersion")
//                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
//                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")


                implementation("de.jensklingenberg.ktorfit:ktorfit-lib:$ktorfitVersion")
                //implementation("de.jensklingenberg.ktorfit:ktorfit-lib-light:$ktorfitVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

                //Only needed when you want to use Kotlin Serialization
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
            }
        }
        val iosMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }

        val macosMain by creating {
            dependsOn(commonMain)
        }
        val macosX64Main by getting {
            dependsOn(macosMain)
        }
        val macosArm64Main by getting {
            dependsOn(macosMain)
        }
        val jsMain by getting {
            dependencies {

            }
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
//    dependencies {
//        api(project(":compose-mvi"))
//        api(project(":BasicCompose"))
//    }
}

dependencies {

    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosX64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspDesktop", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspIosSimulatorArm64", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
//    add("kspJs", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
}
