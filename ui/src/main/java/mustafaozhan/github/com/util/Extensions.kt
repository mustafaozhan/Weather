package mustafaozhan.github.com.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import timber.log.Timber
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
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

private const val RECEIVED_DATE_FORMAT = "yy-MM-dd hh:mm:ss"
private const val EXPECTED_DATE_FORMAT = "ha EEE, dd MMM"

@SuppressLint("SimpleDateFormat")
fun String.format(): String = SimpleDateFormat(
    RECEIVED_DATE_FORMAT
).parse(this)?.let {
    SimpleDateFormat(EXPECTED_DATE_FORMAT).format(it)
} ?: ""

fun View.showLoading(visible: Boolean) = if (visible) {
    visible()
    bringToFront()
} else gone()

fun View?.visible() {
    this?.visibility = View.VISIBLE
}

fun View?.gone() {
    this?.visibility = View.GONE
}

fun TextView.showDetailOrHide(resourceId: Int, stringText: String) {
    if (stringText.isEmpty()) {
        gone()
    } else {
        setSpannedTextForDetailView(context.getString(resourceId, stringText))
    }
}

private const val TEXT_PADDING = 150

fun TextView.setSpannedTextForDetailView(string: String) {
    var tempText = string

    while (measuredWidth < Resources.getSystem().displayMetrics.widthPixels - TEXT_PADDING) {
        tempText = tempText.addChar(" ", tempText.indexOfFirst { it == ':' } + 1)
        text = tempText
        measure(0, 0)
    }
}

fun String.addChar(ch: String, position: Int): String {
    val sb = StringBuilder(this)
    sb.insert(position, ch)
    return sb.toString()
}
