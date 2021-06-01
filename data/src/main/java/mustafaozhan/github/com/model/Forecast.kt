package mustafaozhan.github.com.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    val clouds: Clouds? = null,
    val dt: Int? = null,
    val dtTxt: String? = null,
    val main: Main? = null,
    val pop: Double? = null,
    val rain: Rain? = null,
    val sys: Sys? = null,
    val visibility: Int? = null,
    val weather: List<Weather>? = null,
    val wind: Wind? = null
) : Parcelable
