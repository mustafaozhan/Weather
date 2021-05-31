plugins {
    with(Plugins) {
        id(application)
        kotlin(android)
        id(dependencyUpdates) version Versions.dependencyUpdates
    }
}

android {
    with(ProjectSettings) {
        compileSdkVersion(compileSdkVersion)

        defaultConfig {
            minSdk = ProjectSettings.minSdkVersion
            targetSdk = ProjectSettings.targetSdkVersion

            multiDexEnabled = true
            applicationId = projectId

            versionCode = getVersionCode(project)
            versionName = getVersionName(project)
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
        implementation(koinCore)
        implementation(koinAndroid)
        implementation(roomRuntime)
    }

    with(Modules) {
        implementation(project(ui))
        implementation(project(client))
        implementation(project(data))
        implementation(project(basemob))
    }
}
