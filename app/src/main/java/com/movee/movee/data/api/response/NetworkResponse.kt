package com.movee.movee.data.api.response

import com.movee.movee.commons.exceptions.HttpException
import java.io.IOException


typealias Headers = Map<String, List<String>>

sealed class NetworkResponse<out T> {
    data class Success<T>(
        val statusCode: Int,
        val headers: Headers,
        val value: T,
    ) : NetworkResponse<T>()

    sealed class Failure(val cause: Throwable) : NetworkResponse<Nothing>() {
        data class Http(
            val statusCode: Int,
            val errorBody: String,
        ) : Failure(HttpException(statusCode))

        data class IO(
            val exception: IOException,
        ) : Failure(exception)

        data class Unknown(
            val exception: Throwable,
        ) : Failure(exception)
    }
}

fun <T> NetworkResponse<T>.getOrNull(): T? = when (this) {
    is NetworkResponse.Success -> value
    is NetworkResponse.Failure -> null
}

fun <T> NetworkResponse<T>.getOrThrow(): T = when (this) {
    is NetworkResponse.Success -> value
    is NetworkResponse.Failure.Http -> throw HttpException(statusCode)
    is NetworkResponse.Failure.IO -> throw exception
    is NetworkResponse.Failure.Unknown -> throw exception
}

fun <T> NetworkResponse<T>.toResult(): Result<T> = Result.runCatching { getOrThrow() }

inline fun <T, R> NetworkResponse<T>.map(
    mapper: (T) -> R,
): NetworkResponse<R> = when (this) {
    is NetworkResponse.Success -> NetworkResponse.Success(statusCode, headers, mapper(value))
    is NetworkResponse.Failure -> this
}





