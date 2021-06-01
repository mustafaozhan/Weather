package mustafaozhan.github.com.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    val feelsLike: Double? = null,
    val grndLevel: Int? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    val seaLevel: Int? = null,
    val temp: Double? = null,
    val tempKf: Double? = null,
    val tempMax: Double? = null,
    val tempMin: Double? = null
) : Parcelable
