import dev.iurysouza.modulegraph.Orientation
import groovy.json.JsonSlurper

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.modulegraph)
}

val configuration = JsonSlurper().parse(File(rootProject.projectDir, "configuration.json")) as Map<*, *>
ext["configuration"] = configuration

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin(libs.plugins.android.library.get().pluginId)) {
            plugins.withId("com.android.library") {
                extensions.configure<com.android.build.gradle.LibraryExtension>("android") {
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

                    @Suppress("UnstableApiUsage")
                    testOptions {
                        unitTests.isReturnDefaultValues = true
                    }

                    publishing {
                        multipleVariants {
                            allVariants()
                            withJavadocJar()
                        }
                    }
                }
            }
        }

        if (plugins.hasPlugin(libs.plugins.android.library.get().pluginId) && true == configuration["PUBLISH"]) {
            apply(plugin = "maven-publish")

            extensions.getByType<PublishingExtension>().run {
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
    }
}

moduleGraphConfig {
    readmePath.set("./README.md")
    heading = "## Module Graph"

    setStyleByModuleType = true
    orientation.set(Orientation.TOP_TO_BOTTOM)
}

tasks.register("printVersion") {
    doLast {
        println(rootProject.libs.versions.ui.get())
    }
}
