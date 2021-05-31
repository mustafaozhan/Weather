package mustafaozhan.github.com.di.koin

import mustafaozhan.github.com.viewmodel.detail.DetailViewModel
import mustafaozhan.github.com.viewmodel.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val clientModule = module {
    viewModel { ForecastViewModel(get()) }
    viewModel { DetailViewModel() }
}
