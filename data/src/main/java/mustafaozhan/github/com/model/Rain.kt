package mustafaozhan.github.com.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    val threeHours: Double?
) : Parcelable
