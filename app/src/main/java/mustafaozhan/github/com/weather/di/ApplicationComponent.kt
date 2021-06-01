/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.weather.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import mustafaozhan.github.com.di.ActivityInjectionModule
import mustafaozhan.github.com.di.ActivityScope
import mustafaozhan.github.com.di.ApiModule
import mustafaozhan.github.com.di.AppDatabaseModule
import mustafaozhan.github.com.di.FragmentInjectionModule
import mustafaozhan.github.com.di.FragmentScope
import mustafaozhan.github.com.di.ViewModelModule
import mustafaozhan.github.com.weather.WeatherApp
import javax.inject.Singleton

@Singleton
@ActivityScope
@FragmentScope
@Component(
    modules = [
        // app
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        // ui
        ActivityInjectionModule::class,
        FragmentInjectionModule::class,
        // client
        ViewModelModule::class,
        // data
        ApiModule::class,
        AppDatabaseModule::class,
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: WeatherApp): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: WeatherApp)
}
