package mustafaozhan.github.com.util

import android.widget.ImageView
import timber.log.Timber
import java.io.FileNotFoundException
import java.util.Locale

fun ImageView.getWeatherIconByName(name: String?) {
    setImageResource(
        try {
            context.resources.getIdentifier(
                "icon_" + name?.toLowerCase(Locale.ROOT),
                "drawable",
                context.packageName
            )
        } catch (e: FileNotFoundException) {
            Timber.e(e)
            -1
        }
    )
}
