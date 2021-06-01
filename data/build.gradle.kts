import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    with(Plugins) {
        id(androidLibrary)
        kotlin(android)
        id(parcelize)
        kotlin(kapt)
    }
}

android {
    with(ProjectSettings) {
        compileSdkVersion(compileSdkVersion)

        defaultConfig {
            minSdk = ProjectSettings.minSdkVersion
            targetSdk = ProjectSettings.targetSdkVersion

            javaCompileOptions {
                annotationProcessorOptions {
                    arguments(mapOf("room.schemaLocation" to "$projectDir/schemas"))
                }
            }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }

        buildTypes {
            all {
                val prob = Properties().apply {
                    load(project.rootProject.file("local.properties").inputStream())
                }
                buildConfigField(
                    Types.string,
                    Keys.apiUrl,
                    System.getenv(Keys.apiUrl)?.toString()?.encode()
                        ?: prob[Keys.apiUrl].toString().encode()
                )
                buildConfigField(
                    Types.string,
                    Keys.appId,
                    System.getenv(Keys.appId)?.toString()?.encode()
                        ?: prob[Keys.appId].toString().encode()
                )
            }
        }
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(kotlin)
        implementation(dagger)
        implementation(coroutines)
        implementation(retrofit)
        implementation(moshi)
        implementation(moshiConverter)
        implementation(okhttpInterceptor)
        implementation(roomKtx)
    }
    with(Annotations) {
        kapt(moshi)
        kapt(room)
        kapt(daggerCompiler)
        kapt(daggerProcessor)
    }
}
