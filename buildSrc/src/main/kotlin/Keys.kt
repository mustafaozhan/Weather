import org.gradle.api.Project
import java.util.Properties

object Keys {
    const val apiUrl = "API_URL"
    const val appId = "APP_ID"
}

@Suppress("SwallowedException", "TooGenericExceptionCaught")
fun Project.getLocalPropertyByKey(key: String) = try {
    Properties().apply {
        load(rootProject.file("local.properties").inputStream())
    }
} catch (e: Exception) {
    null
}?.get(key)?.toString()

fun String?.encode(): String = if (this == null) {
    ""
} else {
    "\"$this\""
}
