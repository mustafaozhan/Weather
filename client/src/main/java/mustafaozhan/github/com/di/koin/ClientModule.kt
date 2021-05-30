package mustafaozhan.github.com.di.koin

import mustafaozhan.github.com.forecast.ForecastViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val clientModule = module {
    viewModel { ForecastViewModel(get()) }
}
