object Keys {
    val apiUrl = Triple(
        Types.string,
        "API_RUL",
        "http://api.openweathermap.org/".encode()
    )
    val appId = Triple(
        Types.string,
        "APP_ID",
        "428d70aa1268b4be33b6fd9d7f12bc2c".encode()
    )
}

private fun String.encode(): String = "\"$this\""
