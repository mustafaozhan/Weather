package mustafaozhan.github.com.model

data class ForecastResponse(
    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val list: List<Forecast>?,
    val message: Int?
)
