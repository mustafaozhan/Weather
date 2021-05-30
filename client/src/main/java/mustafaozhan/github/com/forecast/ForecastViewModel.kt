package mustafaozhan.github.com.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mustafaozhan.github.com.data.api.ApiRepository
import mustafaozhan.github.com.error.HttpRequestException
import mustafaozhan.github.com.forecast.ForecastData.Companion.ERROR_CODE_NOT_FOUND
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

    private val data = ForecastData()
    // endregion

    init {
        getForecast()
    }

    private fun getForecast() {
        _state.update(isLoading = true)
        viewModelScope.launch {
            apiRepository
                .getForecast(data.query)
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

    private fun getForecastFailed(throwable: Throwable) {
        Timber.e(throwable)
        _state.update(
            cityName = "",
            country = "",
            forecastList = listOf(),
            isLoading = false
        )
        viewModelScope.launch {
            if (throwable is HttpRequestException && throwable.code == ERROR_CODE_NOT_FOUND) {
                _effect.emit(ForecastEffect.CityNotFound)
            } else {
                _effect.emit(ForecastEffect.Error)
            }
        }
    }

    private fun getForecastCompleted() = _state.update(isLoading = false)

    override fun onQueryChange(query: String) {
        data.query = query
        getForecast()
    }
}
