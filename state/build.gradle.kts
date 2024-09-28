plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "kr.lul.android.ui.state"
    compileSdk = 34

    defaultConfig {
        minSdk = 29

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.text)
    api(libs.androidx.ui.unit)
    api(libs.androidx.ui.tooling)

    implementation(platform(libs.androidx.compose.bom))

    testImplementation(libs.kotlin.logging)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.logback.classic)
}