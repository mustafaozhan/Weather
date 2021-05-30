package mustafaozhan.github.com.weather

import kotlinx.coroutines.flow.MutableStateFlow

data class WeatherState(
    val cityName: String = ""
) {
    companion object {
        fun MutableStateFlow<WeatherState>.update(
            cityName: String = value.cityName,
        ) {
            value = value.copy(
                cityName = cityName
            )
        }
    }
}

interface WeatherEvent

sealed class WeatherEffect
