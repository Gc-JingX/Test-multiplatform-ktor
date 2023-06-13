
plugins {
    id("com.android.library")
}
android {
    namespace ="com.dd.basiccompose"
    compileSdk=33

    buildFeatures {
        compose = true
    }
    defaultConfig {
        minSdk =24
        targetSdk =33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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

}