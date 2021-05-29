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
    }
}

dependencies {
    with(Dependencies.Android) {
        implementation(kotlin)
        implementation(lifecycleViewModel)
        implementation(lifecycleRuntime)
        implementation(coroutines)
        implementation(dagger)
    }
    with(Modules) {
        implementation(project(data))
    }
    with(Annotations) {
        kapt(daggerCompiler)
        kapt(daggerProcessor)
    }
}
