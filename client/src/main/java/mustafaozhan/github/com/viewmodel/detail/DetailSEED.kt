package mustafaozhan.github.com.viewmodel.detail

import kotlinx.coroutines.flow.MutableStateFlow
import mustafaozhan.github.com.model.Forecast
import mustafaozhan.github.com.model.WeatherStatus

data class DetailState(
    val imgName: String = "",
    val description: String = "",
    val date: String = "",
    val temperature: String = "",
    val minDegree: String = "",
    val maxDegree: String = "",
    val humidity: String = "",
    val wind: String = "",
    val seaLevel: String = "",
    val visibility: String = "",
    val pressure: String = "",
    val status: WeatherStatus = WeatherStatus.UNKNOWN
) {
    companion object {
        @Suppress("LongParameterList")
        fun MutableStateFlow<DetailState>.update(
            imgName: String = value.imgName,
            description: String = value.description,
            date: String = value.date,
            temperature: String = value.temperature,
            minDegree: String = value.minDegree,
            maxDegree: String = value.maxDegree,
            humidity: String = value.humidity,
            wind: String = value.wind,
            seaLevel: String = value.seaLevel,
            visibility: String = value.visibility,
            pressure: String = value.pressure,
            status: WeatherStatus = value.status
        ) {
            value = value.copy(
                imgName = imgName,
                description = description,
                date = date,
                temperature = temperature,
                minDegree = minDegree,
                maxDegree = maxDegree,
                humidity = humidity,
                wind = wind,
                seaLevel = seaLevel,
                visibility = visibility,
                pressure = pressure,
                status = status
            )
        }
    }
}

interface DetailEvent {
    fun onBackClick()
}

sealed class DetailEffect {
    object Back : DetailEffect()
}

class DetailData {
    lateinit var forecast: Forecast

    companion object {
        const val COLD_WEATHER_INDICATOR = 10
        const val HOT_WEATHER_INDICATOR = 15
    }
}
