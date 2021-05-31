/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mustafaozhan.github.com.ui.DetailFragment
import mustafaozhan.github.com.ui.ForecastFragment
import mustafaozhan.github.com.ui.HistoryFragment

@Suppress("unused")
@Module
abstract class FragmentInjectionModule {

    @ContributesAndroidInjector
    abstract fun contributesForecastFragment(): ForecastFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun contributesHistoryFragment(): HistoryFragment
}
