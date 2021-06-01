/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.di

import dagger.Module
import dagger.Provides
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.data.db.HistoryRepository
import mustafaozhan.github.com.viewmodel.detail.DetailViewModel
import mustafaozhan.github.com.viewmodel.forecast.ForecastViewModel
import mustafaozhan.github.com.viewmodel.history.HistoryViewModel

@Module
class ViewModelModule {

    @Provides
    @ActivityScope
    internal fun providesWeatherViewModel(
        apiRepository: ApiRepository,
        historyRepository: HistoryRepository
    ) = ForecastViewModel(
        apiRepository,
        historyRepository
    )

    @Provides
    @ActivityScope
    internal fun providesHistoryViewModel(
        historyRepository: HistoryRepository
    ) = HistoryViewModel(
        historyRepository
    )

    @Provides
    @ActivityScope
    internal fun providesDetailViewModel() = DetailViewModel()
}
