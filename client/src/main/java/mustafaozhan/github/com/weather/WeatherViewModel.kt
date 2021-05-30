package mustafaozhan.github.com.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.weather.WeatherState.Companion.update
import timber.log.Timber

class WeatherViewModel(
    private val apiRepository: ApiRepository
) : ViewModel(), WeatherEvent {
    // region SEED
    private val _state = MutableStateFlow(WeatherState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<WeatherEffect>()
    val effect = _effect.asSharedFlow()

    val event = this as WeatherEvent
    // endregion

    init {
        viewModelScope.launch {
            apiRepository.getForecast("berlin")
                .execute(
                    {
                        _state.update(cityName = it.city?.name ?: "")
                    },
                    {
                        Timber.e(it)
                    }
                )
        }
    }
}
