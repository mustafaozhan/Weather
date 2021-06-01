/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mustafaozhan.github.com.ui.MainActivity
import mustafaozhan.github.com.ui.SplashActivity

@Suppress("unused")
@Module
abstract class ActivityInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesSplashActivity(): SplashActivity
}
