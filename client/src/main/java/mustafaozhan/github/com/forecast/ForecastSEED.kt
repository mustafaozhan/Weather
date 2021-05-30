package mustafaozhan.github.com.forecast

import kotlinx.coroutines.flow.MutableStateFlow
import mustafaozhan.github.com.model.Forecast

data class ForecastState(
    val cityName: String = "",
    val forecastList: List<Forecast> = listOf()
) {
    companion object {
        fun MutableStateFlow<ForecastState>.update(
            cityName: String = value.cityName,
            forecastList: List<Forecast> = value.forecastList
        ) {
            value = value.copy(
                cityName = cityName,
                forecastList = forecastList
            )
        }
    }
}

interface ForecastEvent

sealed class ForecastEffect
