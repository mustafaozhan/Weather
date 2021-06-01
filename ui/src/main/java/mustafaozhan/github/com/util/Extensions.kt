package mustafaozhan.github.com.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mustafaozhan.github.com.di.GlideApp
import mustafaozhan.github.com.ui.R
import java.text.SimpleDateFormat

fun ImageView.loadImageUrl(
    imageUrl: String?,
    placeholderImage: Drawable? = ContextCompat.getDrawable(
        context,
        R.drawable.ic_glide_place_holder
    )
) = GlideApp
    .with(context)
    .load(
        // here can be simply "http://openweathermap.org/img/wn/$imageUrl@4x.png"
        // but openweathermap icon's from API really awful so i used local instead
        context.resources.getIdentifier("icon_$imageUrl", "drawable", context.packageName)
    )
    .fallback(placeholderImage)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .centerCrop()
    .placeholder(placeholderImage)
    .error(placeholderImage)
    .into(this)

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
