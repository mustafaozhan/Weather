/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.data.api

import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mustafaozhan.github.com.data.mappers.toModel
import mustafaozhan.github.com.error.HttpRequestException
import mustafaozhan.github.com.error.InternetConnectionException
import mustafaozhan.github.com.error.ModelMappingException
import mustafaozhan.github.com.error.NetworkException
import mustafaozhan.github.com.error.UnknownNetworkException
import mustafaozhan.github.com.util.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

internal class ApiRepositoryImpl(
    private val apiService: ApiService
) : ApiRepository {
    @Suppress("TooGenericExceptionCaught")
    suspend fun <T> apiRequest(
        suspendBlock: suspend () -> T
    ) = withContext(Dispatchers.IO) {
        try {
            Result.Success(suspendBlock())
        } catch (throwable: Throwable) {
            Result.Error(
                when (throwable) {
                    is CancellationException -> throwable
                    is UnknownHostException,
                    is TimeoutException,
                    is IOException,
                    is SSLException -> NetworkException(throwable)
                    is ConnectException -> InternetConnectionException(throwable)
                    is JsonDataException -> ModelMappingException(throwable)
                    is HttpException -> HttpRequestException(
                        message = "${throwable.response()?.code()} ${
                            throwable.response()?.message()
                        }",
                        response = throwable.response().toString(),
                        cause = throwable,
                        code = throwable.code()
                    )
                    else -> UnknownNetworkException(throwable)
                }
            )
        }
    }

    override suspend fun getForecast(query: String) = apiRequest {
        apiService.getForecast(query).toModel()
    }
}
