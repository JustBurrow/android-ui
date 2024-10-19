plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "kr.lul.android.ui.state"

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.text)
    api(libs.androidx.ui.unit)
    api(libs.androidx.ui.tooling)

    implementation(platform(libs.androidx.compose.bom))
}