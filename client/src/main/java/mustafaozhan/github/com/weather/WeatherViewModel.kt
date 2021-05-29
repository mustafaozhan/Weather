package mustafaozhan.github.com.weather

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import mustafaozhan.github.com.base.BaseData
import mustafaozhan.github.com.base.BaseViewModel

class WeatherViewModel : BaseViewModel(), WeatherEvent {
    // region SEED
    private val _state = MutableStateFlow(WeatherState())
    override val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<WeatherEffect>()
    override val effect = _effect.asSharedFlow()

    override val event = this as WeatherEvent

    override val data: BaseData? = null
    // endregion
}
