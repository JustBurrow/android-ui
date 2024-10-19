plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

val configuration = rootProject.ext["CONFIGURATION"] as Map<*, *>

android {
    namespace = "kr.lul.android.ui.scaffold"

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    if (true == configuration["PUBLISH"]) {
        api("kr.lul.andoird.ui:compose:${rootProject.ext["PUBLISH_VERSION"]}")
        api("kr.lul.andoird.ui:navigation:${rootProject.ext["PUBLISH_VERSION"]}")
        api("kr.lul.andoird.ui:viewmodel:${rootProject.ext["PUBLISH_VERSION"]}")
    } else {
        api(projects.compose)
        api(projects.navigation)
        api(projects.viewmodel)
    }

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.hilt)

    ksp(libs.hilt.compiler)
}