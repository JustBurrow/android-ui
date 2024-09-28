plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)

    `maven-publish`
}

val configuration = rootProject.ext["CONFIGURATION"] as Map<*, *>

android {
    namespace = "kr.lul.android.ui.navigation"

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    if (true == configuration["PUBLISH"]) {
        api("kr.lul.andoird.ui:compose:${rootProject.ext["UI_VERSION"]}")
    } else {
        api(projects.compose)
    }
    api(libs.androidx.navigation.compose)
    api(libs.hilt)
    api(libs.hilt.navigation.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)

    ksp(libs.hilt.compiler)

    debugImplementation(libs.androidx.ui.test.manifest)
    debugImplementation(libs.androidx.ui.tooling)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}