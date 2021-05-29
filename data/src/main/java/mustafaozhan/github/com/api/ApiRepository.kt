/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.api

import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mustafaozhan.github.com.error.InternetConnectionException
import mustafaozhan.github.com.error.ModelMappingException
import mustafaozhan.github.com.error.NetworkException
import mustafaozhan.github.com.error.RetrofitException
import mustafaozhan.github.com.error.UnknownNetworkException
import mustafaozhan.github.com.util.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

class ApiRepository(private val apiFactory: ApiFactory) {

    @Suppress("ThrowsCount", "TooGenericExceptionCaught")
    private suspend fun <T> apiRequest(suspendBlock: suspend () -> T) =
        withContext(Dispatchers.IO) {
            try {
                Result.Success(suspendBlock.invoke())
            } catch (e: Throwable) {
                Result.Error(
                    when (e) {
                        is CancellationException -> e
                        is UnknownHostException,
                        is TimeoutException,
                        is IOException,
                        is SSLException -> NetworkException(e)
                        is ConnectException -> InternetConnectionException(e)
                        is JsonDataException -> ModelMappingException(e)
                        is HttpException -> RetrofitException(
                            e.response()?.code().toString() + " " + e.response()?.message(),
                            e.response().toString(),
                            e
                        )
                        else -> UnknownNetworkException(e)
                    }
                )
            }
        }

    suspend fun getForecast(query: String) = apiRequest {
        apiFactory.apiService.getForecast(query)
    }
}
