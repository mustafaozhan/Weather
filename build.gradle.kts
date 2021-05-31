// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.dependencyUpdates) version Versions.dependencyUpdates
}
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        with(ClassPaths) {
            classpath(androidBuildTools)
            classpath(kotlinGradlePlugin)
            classpath(navigation)
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
