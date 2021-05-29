plugins {
    with(Plugins) {
        id(androidLibrary)
        kotlin(android)
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
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(kotlin)
        implementation(lifecycleViewModel)
        implementation(lifecycleRuntime)
        implementation(coroutines)
        implementation(koinCore)
        implementation(koinAndroid)
    }
    with(Modules) {
        implementation(project(data))
    }
}
