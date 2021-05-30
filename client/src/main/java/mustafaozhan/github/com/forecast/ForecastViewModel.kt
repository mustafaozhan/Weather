package mustafaozhan.github.com.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.forecast.ForecastState.Companion.update
import mustafaozhan.github.com.model.ForecastResponse
import timber.log.Timber

class ForecastViewModel(
    private val apiRepository: ApiRepository
) : ViewModel(), ForecastEvent {
    // region SEED
    private val _state = MutableStateFlow(ForecastState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<ForecastEffect>()
    val effect = _effect.asSharedFlow()

    val event = this as ForecastEvent
    // endregion

    init {
        viewModelScope.launch {
            apiRepository
                .getForecast("berlin")
                .execute(
                    ::getForecastSuccessful,
                    ::getForecastFailed,
                    ::getForecastCompleted
                )
        }
    }

    private fun getForecastSuccessful(
        forecastResponse: ForecastResponse
    ) = with(forecastResponse) {
        _state.update(
            cityName = city?.name ?: "",
            country = city?.country ?: "",
            forecastList = list ?: listOf()
        )
    }

    private fun getForecastFailed(throwable: Throwable) = Timber.e(throwable)

    private fun getForecastCompleted() = _state.update(isLoading = false)
}
