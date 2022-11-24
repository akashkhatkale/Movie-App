package com.movee.movee.data.api.response

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class NetworkResponseCall(
    private val delegate: Call<Any?>,
    private val successMapper: (Response<Any?>) -> NetworkResponse<Any?>,
) : Call<NetworkResponse<Any?>> {
    // This is wanted. Exceptions should be wrapped in Result class in order to preserve referential transparency.
    @Suppress("TooGenericExceptionCaught")
    override fun execute(): Response<NetworkResponse<Any?>> {
        val result = try {
            delegate.execute().toResult()
        } catch (ioException: IOException) {
            NetworkResponse.Failure.IO(ioException)
        } catch (throwable: Throwable) {
            NetworkResponse.Failure.Unknown(throwable)
        }
        return Response.success(result)
    }

    override fun enqueue(callback: Callback<NetworkResponse<Any?>>) = delegate.enqueue(object : Callback<Any?> {
        override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
            val result = response.toResult()
            callback.onResponse(this@NetworkResponseCall, Response.success(result))
        }

        override fun onFailure(call: Call<Any?>, throwable: Throwable) {
            val failure = when (throwable) {
                is IOException -> NetworkResponse.Failure.IO(throwable)
                else -> NetworkResponse.Failure.Unknown(throwable)
            }

            callback.onResponse(
                this@NetworkResponseCall,
                Response.success(failure)
            )
        }
    })

    private fun Response<Any?>.toResult() = if (isSuccessful) {
        successMapper(this)
    } else {
        NetworkResponse.Failure.Http(code(), errorBody()?.string().orEmpty())
    }

    override fun request(): Request = delegate.request()

    override fun clone() = NetworkResponseCall(delegate.clone(), successMapper)

    override fun isExecuted() = delegate.isExecuted

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun timeout(): Timeout = delegate.timeout()

}