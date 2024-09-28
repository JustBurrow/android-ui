plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.android)

    `maven-publish`
}

val configuration = rootProject.ext["CONFIGURATION"] as Map<*, *>

android {
    namespace = "kr.lul.android.ui.compose"

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

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)

    debugImplementation(libs.androidx.ui.tooling)

    testImplementation(libs.junit)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}