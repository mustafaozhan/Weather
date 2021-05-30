package mustafaozhan.github.com.forecast

import kotlinx.coroutines.flow.MutableStateFlow
import mustafaozhan.github.com.model.Forecast

data class ForecastState(
    val cityName: String = "",
    val country: String = "",
    val forecastList: List<Forecast> = listOf(),
    val isLoading: Boolean = true
) {
    companion object {
        fun MutableStateFlow<ForecastState>.update(
            cityName: String = value.cityName,
            country: String = value.country,
            forecastList: List<Forecast> = value.forecastList,
            isLoading: Boolean = value.isLoading
        ) {
            value = value.copy(
                cityName = cityName,
                country = country,
                forecastList = forecastList,
                isLoading = isLoading
            )
        }
    }
}

interface ForecastEvent {
    fun onQueryChange(query: String)
}

sealed class ForecastEffect {
    object Error : ForecastEffect()
    object CityNotFound : ForecastEffect()
}

data class ForecastData(
    var query: String = "Berlin"
) {
    companion object {
        const val ERROR_CODE_NOT_FOUND = 404
    }
}
