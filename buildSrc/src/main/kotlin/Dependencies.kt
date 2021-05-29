@Suppress("SpellCheckingInspection")
object Dependencies {
    object Android {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val androidMaterial =
            "com.google.android.material:material:${Versions.androidMaterial}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleExt}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleExt}"
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val dagger = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
        const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val anrWatchDog = "com.github.anrwatchdog:anrwatchdog:${Versions.anrWatchDog}"
        const val moshiConverter =
            "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}"
        const val okhttpInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpInterceptor}"
        const val jUnit = "junit:junit:${Versions.jUnit}"
        const val mockK = "io.mockk:mockk:${Versions.mockK}"
        const val archTesting = "android.arch.core:core-testing:${Versions.archTesting}"
        const val coroutinesTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }
}
