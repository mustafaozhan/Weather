/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.weather.di

import android.content.Context
import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.weather.WeatherApp
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun providesContext(application: WeatherApp): Context = application.applicationContext
}
