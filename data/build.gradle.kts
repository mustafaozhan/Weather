plugins {
    with(Plugins) {
        id(androidLibrary)
        kotlin(android)
        kotlin(kapt)
    }
}

android {
    with(ProjectSettings) {
        compileSdkVersion(compileSdkVersion)

        defaultConfig {
            minSdk = ProjectSettings.minSdkVersion
            targetSdk = ProjectSettings.targetSdkVersion
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
                with(Keys) {
                    buildConfigField(apiUrl.first, apiUrl.second, apiUrl.third)
                    buildConfigField(appId.first, appId.second, appId.third)
                }
            }
        }
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(kotlin)
        implementation(koinCore)
        implementation(coroutines)
        implementation(retrofit)
        implementation(moshi)
        implementation(moshiConverter)
        implementation(okhttpInterceptor)
    }
    with(Annotations) {
        kapt(moshi)
    }
}
