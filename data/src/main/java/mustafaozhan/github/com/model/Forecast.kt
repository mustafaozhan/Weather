package mustafaozhan.github.com.model

data class Forecast(
    val clouds: Clouds?,
    val dt: Int?,
    val dtTxt: String?,
    val main: Main?,
    val pop: Double?,
    val rain: Rain?,
    val sys: Sys?,
    val visibility: Int?,
    val weather: List<Weather>?,
    val wind: Wind?
)
