package mustafaozhan.github.com.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mustafaozhan.github.com.model.Forecast
import mustafaozhan.github.com.model.WeatherStatus
import mustafaozhan.github.com.viewmodel.detail.DetailData.Companion.COLD_WEATHER_INDICATOR
import mustafaozhan.github.com.viewmodel.detail.DetailData.Companion.HOT_WEATHER_INDICATOR
import mustafaozhan.github.com.viewmodel.detail.DetailState.Companion.update

class DetailViewModel : ViewModel(), DetailEvent {
    // region SEED
    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DetailEffect>()
    val effect = _effect.asSharedFlow()

    val event = this as DetailEvent

    val data = DetailData()
    // endregion

    fun setData(forecast: Forecast) {
        data.forecast = forecast

        with(data.forecast) {
            _state.update(
                imgName = weather?.firstOrNull()?.icon ?: "",
                description = weather?.firstOrNull()?.description ?: "",
                date = dtTxt ?: "",
                temperature = main?.temp?.toString() ?: "",
                minDegree = main?.tempMin?.toString() ?: "",
                maxDegree = main?.tempMax?.toString() ?: "",
                humidity = main?.humidity?.toString() ?: "",
                wind = wind?.speed?.toString() ?: "",
                seaLevel = main?.seaLevel?.toString() ?: "",
                visibility = visibility?.toString() ?: "",
                pressure = main?.pressure?.toString() ?: "",
                status = getStatus(forecast)
            )
        }
    }

    private fun getStatus(forecast: Forecast) = when {
        forecast.main?.temp == null -> WeatherStatus.UNKNOWN
        forecast.main!!.temp!! > HOT_WEATHER_INDICATOR -> WeatherStatus.HOT
        forecast.main!!.temp!! < COLD_WEATHER_INDICATOR -> WeatherStatus.COLD
        else -> WeatherStatus.UNKNOWN
    }

    // event
    override fun onBackClick() {
        viewModelScope.launch {
            _effect.emit(DetailEffect.Back)
        }
    }
}
