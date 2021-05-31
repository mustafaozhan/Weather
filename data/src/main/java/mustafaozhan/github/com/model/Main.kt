package mustafaozhan.github.com.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Main(
    val feelsLike: Double?,
    val grndLevel: Int?,
    val humidity: Int?,
    val pressure: Int?,
    val seaLevel: Int?,
    val temp: Double?,
    val tempKf: Double?,
    val tempMax: Double?,
    val tempMin: Double?
) : Parcelable
