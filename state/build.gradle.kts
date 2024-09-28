import groovy.json.JsonSlurper

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    `maven-publish`
}

val configuration = JsonSlurper().parse(File(rootProject.projectDir, "configuration.json")) as Map<*, *>

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

    if (true == configuration["PUBLISH"] as Boolean?) {
        publishing {
            multipleVariants {
                allVariants()
                withJavadocJar()
            }
        }
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

if (true == configuration["PUBLISH"] as Boolean?) {
    publishing {
        repositories {
            maven {
                name = "LulKr"
                url = uri("https://maven.pkg.github.com/JustBurrow/packages")
                credentials {
                    username = configuration["PUBLISH_PACKAGES_USER"] as String?
                        ?: System.getenv("PUBLISH_PACKAGES_USER")
                    password = configuration["PUBLISH_PACKAGES_TOKEN"] as String?
                        ?: System.getenv("PUBLISH_PACKAGES_TOKEN")
                }
            }
        }

        publications {
            register<MavenPublication>("release") {
                groupId = "kr.lul.andoird.ui"
                artifactId = project.name.lowercase()
                version = libs.versions.ui.get()

                pom {
                    scm {
                        url = "https://github.com/JustBurrow/android-ui"
                    }
                }

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
}