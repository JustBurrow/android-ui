plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "kr.lul.android.ui.test"

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(libs.kotest.runner.junit5)
    api(libs.kotlin.logging)
    api(libs.logback.classic)
    api(libs.mockk)
}