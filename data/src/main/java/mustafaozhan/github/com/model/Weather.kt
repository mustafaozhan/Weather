package mustafaozhan.github.com.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val description: String?,
    val icon: String?,
    val id: Int?,
    val main: String?
) : Parcelable
