package mustafaozhan.github.com.viewmodel.detail

import kotlinx.coroutines.flow.MutableStateFlow
import mustafaozhan.github.com.model.Forecast

data class DetailState(
    val imgName: String = "",
    val description: String = "",
    val date: String = "",
    val minDegree: String = "",
    val maxDegree: String = "",
    val humidity: String = "",
    val wind: String = "",
    val seaLevel: String = ""
) {
    companion object {
        @Suppress("LongParameterList")
        fun MutableStateFlow<DetailState>.update(
            imgName: String = value.imgName,
            description: String = value.description,
            date: String = value.date,
            minDegree: String = value.minDegree,
            maxDegree: String = value.maxDegree,
            humidity: String = value.humidity,
            wind: String = value.wind,
            seaLevel: String = value.seaLevel
        ) {
            value = value.copy(
                imgName = imgName,
                description = description,
                date = date,
                minDegree = minDegree,
                maxDegree = maxDegree,
                humidity = humidity,
                wind = wind,
                seaLevel = seaLevel
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
}
