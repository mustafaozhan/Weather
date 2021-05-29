package mustafaozhan.github.com.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Clouds(
    @Json(name = "all") val all: Int?
)
