package mustafaozhan.github.com.di.koin

import mustafaozhan.github.com.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val clientModule = module {
    viewModel { WeatherViewModel() }
}
