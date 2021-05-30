/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.data.api

import mustafaozhan.github.com.data.mappers.toModel

internal class ApiRepositoryImpl(
    private val apiService: ApiService
) : BaseRepository(), ApiRepository {

    override suspend fun getForecast(query: String) = apiRequest {
        apiService.getForecast(query).toModel()
    }
}
