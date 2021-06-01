package mustafaozhan.github.com.model

data class ForecastResponse(
    val city: City? = null,
    val cnt: Int? = null,
    val cod: String? = null,
    val list: List<Forecast>? = null,
    val message: Int? = null
)
