package mustafaozhan.github.com.weather

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mustafaozhan.github.com.api.ApiRepository
import mustafaozhan.github.com.base.BaseData
import mustafaozhan.github.com.base.BaseViewModel
import timber.log.Timber

class WeatherViewModel(
    private val apiRepository: ApiRepository
) : BaseViewModel(), WeatherEvent {
    // region SEED
    private val _state = MutableStateFlow(WeatherState())
    override val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<WeatherEffect>()
    override val effect = _effect.asSharedFlow()

    override val event = this as WeatherEvent

    override val data: BaseData? = null
    // endregion

    init {
        viewModelScope.launch {
            apiRepository.getForecast("berlin")
                .execute(
                    {
                        Timber.i(it.toString())
                    },
                    {
                        Timber.e(it)
                    }
                )
        }
    }
}
