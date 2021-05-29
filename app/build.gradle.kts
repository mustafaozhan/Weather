plugins {
    with(Plugins) {
        id(application)
        kotlin(android)
        id(dependencyUpdates) version Versions.dependencyUpdates
        kotlin(kapt)
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
        implementation(dagger)
    }

    kapt(Annotations.daggerCompiler)

    with(Modules) {
        implementation(project(ui))
        implementation(project(client))
    }
}
