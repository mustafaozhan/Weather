plugins {
    with(Plugins) {
        id(androidLibrary)
        kotlin(android)
        id(safeargs)
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
        buildFeatures {
            viewBinding = true
        }
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(kotlin)
        implementation(androidMaterial)
        implementation(constraintLayout)
        implementation(navigation)
        implementation(koinCore)
        implementation(koinAndroid)
        implementation(timber)
        implementation(navigation)
    }
    with(Modules) {
        implementation(project(basemob))
        implementation(project(client))
    }
}
