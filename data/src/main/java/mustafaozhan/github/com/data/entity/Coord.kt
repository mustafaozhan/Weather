package mustafaozhan.github.com.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Coord(
    @Json(name = "lat") val lat: Double?,
    @Json(name = "lon") val lon: Double?
)
