/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.di

import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.weather.WeatherViewModel

@Module
class ViewModelModule {

    @Provides
    @ActivityScope
    internal fun providesWeatherViewModel() = WeatherViewModel()
}
