package mustafaozhan.github.com.data.api

import mustafaozhan.github.com.model.ForecastResponse
import mustafaozhan.github.com.util.Result

interface ApiRepository {
    suspend fun getForecast(query: String): Result<out ForecastResponse>
}
