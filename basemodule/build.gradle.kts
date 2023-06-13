plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    js(IR) {
        browser()
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
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "basemodule"
        }
    }

    sourceSets {
        val commonMain by getting{
            dependencies {

                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                implementation("com.google.accompanist:accompanist-webview:0.28.0")
                implementation("com.google.accompanist:accompanist-permissions:0.28.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

            }
        }
        val androidMain by getting{
            dependencies {
                implementation("androidx.navigation:navigation-compose:2.6.0-alpha01")
                implementation("androidx.core:core-ktx:1.9.0")
                implementation("androidx.appcompat:appcompat:1.6.0")
                implementation("androidx.palette:palette-ktx:1.0.0")
                implementation("androidx.startup:startup-runtime:1.2.0-alpha01")
                implementation("androidx.compose.foundation:foundation:1.4.1")
                implementation("androidx.paging:paging-runtime:3.2.0-alpha04")
                implementation("androidx.paging:paging-compose:1.0.0-alpha18")
                implementation("androidx.compose.material3:material3:1.0.1")
                implementation("androidx.compose.material:material-ripple:1.4.1")
                implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha05")
                implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha05")
                implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha05")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha05")
                implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha05")
                implementation("androidx.startup:startup-runtime:1.2.0-alpha01")
                implementation("androidx.compose.material:material-icons-extended:1.4.1")
                implementation("androidx.compose.ui:ui-tooling-preview:1.4.1")
                implementation("androidx.compose.ui:ui-tooling:1.4.1")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.johnny.basemodule"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }
}
dependencies {
    api("androidx.navigation:navigation-compose:2.6.0-alpha01")
    api("androidx.core:core-ktx:1.9.0")
    api("androidx.appcompat:appcompat:1.6.0")
    api("androidx.palette:palette-ktx:1.0.0")
    api("androidx.startup:startup-runtime:1.2.0-alpha01")
    api("androidx.compose.foundation:foundation:1.4.1")
    api("androidx.paging:paging-runtime:3.2.0-alpha04")
    api("androidx.paging:paging-compose:1.0.0-alpha18")
    api("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    api("androidx.compose.material3:material3:1.0.1")
    api("androidx.compose.material:material-ripple:1.4.1")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha05")
    api("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha05")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha05")
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha05")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha05")
    api("androidx.startup:startup-runtime:1.2.0-alpha01")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    api("androidx.compose.material:material-icons-extended:1.4.1")
    api("androidx.compose.ui:ui-tooling-preview:1.4.1")
    api("androidx.compose.ui:ui-tooling:1.4.1")
    api("com.google.accompanist:accompanist-webview:0.28.0")
    api("com.google.accompanist:accompanist-permissions:0.28.0")
//    implementation(project(mapOf("path" to ":androidx.navigation:navigation-compose:2.6.0-alpha01")))
//    implementation(project(mapOf("path" to ":androidx.core:core-ktx:1.9.0")))
//    implementation(project(mapOf("path" to ":androidx.appcompat:appcompat:1.6.0")))
//    implementation(project(mapOf("path" to ":androidx.palette:palette-ktx:1.0.0")))
//    implementation(project(mapOf("path" to ":androidx.startup:startup-runtime:1.2.0-alpha01")))
//    implementation(project(mapOf("path" to ":androidx.compose.foundation:foundation:1.4.1")))
//    implementation(project(mapOf("path" to ":androidx.paging:paging-runtime:3.2.0-alpha04")))
//    implementation(project(mapOf("path" to ":androidx.paging:paging-compose:1.0.0-alpha18")))
//    implementation(project(mapOf("path" to ":com.google.accompanist:accompanist-systemuicontroller:0.28.0")))
//    implementation(project(mapOf("path" to ":androidx.compose.material3:material3:1.0.1")))
//    implementation(project(mapOf("path" to ":androidx.compose.material:material-ripple:1.4.1")))
//    implementation(project(mapOf("path" to ":androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha05")))
//    implementation(project(mapOf("path" to ":androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha05")))
//    implementation(project(mapOf("path" to ":androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha05")))
//    implementation(project(mapOf("path" to ":androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha05")))
//    implementation(project(mapOf("path" to ":androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha05")))
//    implementation(project(mapOf("path" to ":androidx.startup:startup-runtime:1.2.0-alpha01")))
//    implementation(project(mapOf("path" to ":org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")))
//    implementation(project(mapOf("path" to ":androidx.compose.material:material-icons-extended:1.4.1")))
//    implementation(project(mapOf("path" to ":androidx.compose.ui:ui-tooling-preview:1.4.1")))
//    implementation(project(mapOf("path" to ":androidx.compose.ui:ui-tooling:1.4.1")))
//    implementation(project(mapOf("path" to ":com.google.accompanist:accompanist-webview:0.28.0")))
//    implementation(project(mapOf("path" to ":com.google.accompanist:accompanist-permissions:0.28.0")))
//    api("androidx.navigation:navigation-compose:2.6.0-alpha01")
//    api("androidx.core:core-ktx:1.9.0")
//    api("androidx.appcompat:appcompat:1.6.0")
//    api("androidx.palette:palette-ktx:1.0.0")
//    api("androidx.startup:startup-runtime:1.2.0-alpha01")
//    api("androidx.compose.foundation:foundation:1.4.1")
//    api("androidx.paging:paging-runtime:3.2.0-alpha04")
//    api("androidx.paging:paging-compose:1.0.0-alpha18")
//    api("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
//    api("androidx.compose.material3:material3:1.0.1")
//    api("androidx.compose.material:material-ripple:1.4.1")
//    api("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha05")
//    api("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha05")
//    api("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha05")
//    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha05")
//    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha05")
//    api("androidx.startup:startup-runtime:1.2.0-alpha01")
//    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
//    api("androidx.compose.material:material-icons-extended:1.4.1")
//    api("androidx.compose.ui:ui-tooling-preview:1.4.1")
//    api("androidx.compose.ui:ui-tooling:1.4.1")
//    api("com.google.accompanist:accompanist-webview:0.28.0")
//    api("com.google.accompanist:accompanist-permissions:0.28.0")

}