object Keys {
    const val apiUrl = "API_URL"
    const val appId = "APP_ID"
}

fun String?.encode(): String = if (this == null) {
    ""
} else {
    "\"$this\""
}
