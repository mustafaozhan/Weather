/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mustafaozhan.github.com.ui.WeatherFragment

@Suppress("unused")
@Module
abstract class FragmentInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributesWeatherFragment(): WeatherFragment
}
