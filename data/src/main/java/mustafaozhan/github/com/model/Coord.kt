package mustafaozhan.github.com.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Coord(
    @Json(name = "lat") val lat: Double?,
    @Json(name = "lon") val lon: Double?
)
