package mustafaozhan.github.com.weather

import mustafaozhan.github.com.base.BaseEffect
import mustafaozhan.github.com.base.BaseEvent
import mustafaozhan.github.com.base.BaseState

data class WeatherState(
    val helloWorldText: String = "Hello World!"
) : BaseState()

interface WeatherEvent : BaseEvent

sealed class WeatherEffect : BaseEffect()
