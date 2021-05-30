/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package mustafaozhan.github.com.error

open class NetworkException(cause: Throwable) : Throwable(cause)

class UnknownNetworkException(cause: Throwable) : NetworkException(cause)

class ModelMappingException(cause: Throwable) : NetworkException(cause)

class InternetConnectionException(cause: Throwable) : NetworkException(cause)

@Suppress("UNUSED_PARAMETER", "unused")
class HttpRequestException(
    override val message: String?,
    val response: String,
    override val cause: Throwable,
    val code: Int
) : NetworkException(cause)
