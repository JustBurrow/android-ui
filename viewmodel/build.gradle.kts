plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)
}

val configuration = rootProject.ext["CONFIGURATION"] as Map<*, *>

android {
    namespace = "kr.lul.android.ui.viewmodel"

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    if (true == configuration["PUBLISH"]) {
        api("kr.lul.andoird.ui:state:${rootProject.ext["PUBLISH_VERSION"]}")
    } else {
        api(projects.state)
    }

    implementation(libs.androidx.lifecycle.viewmodel)
}