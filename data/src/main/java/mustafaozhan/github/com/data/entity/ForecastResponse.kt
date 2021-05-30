package mustafaozhan.github.com.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ForecastResponse(
    @Json(name = "city") val city: City?,
    @Json(name = "cnt") val cnt: Int?,
    @Json(name = "cod") val cod: String?,
    @Json(name = "list") val list: List<Forecast>?,
    @Json(name = "message") val message: Int?
)
