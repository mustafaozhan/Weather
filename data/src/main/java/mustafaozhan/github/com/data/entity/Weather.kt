package mustafaozhan.github.com.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class Weather(
    @Json(name = "description") val description: String?,
    @Json(name = "icon") val icon: String?,
    @Json(name = "id") val id: Int?,
    @Json(name = "main") val main: String?
)
