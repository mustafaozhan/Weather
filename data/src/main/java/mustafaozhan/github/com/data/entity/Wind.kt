package mustafaozhan.github.com.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Wind(
    @Json(name = "deg") val deg: Int?,
    @Json(name = "gust") val gust: Double?,
    @Json(name = "speed") val speed: Double?
)
