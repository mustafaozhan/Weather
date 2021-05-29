/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.api

import mustafaozhan.github.com.data.BuildConfig
import mustafaozhan.github.com.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val DEFAULT_UNITS = "metric"
    }

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("units") units: String = DEFAULT_UNITS,
        @Query("appid") appId: String = BuildConfig.APP_ID
    ): ForecastResponse
}