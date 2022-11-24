package com.movee.movee.data.api.response

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import java.lang.reflect.Type

class NetworkResponseAdapter(
    private val type: Type,
) : CallAdapter<Any?, Call<NetworkResponse<Any?>>> {

    private val voidMapper = { response: Response<Any?> ->
        NetworkResponse.Success(response.code(), response.headers().toMultimap(), null)
    }

    private val unitMapper = { response: Response<Any?> ->
        NetworkResponse.Success(response.code(), response.headers().toMultimap(), Unit)
    }

    private val typeMapper = { response: Response<Any?> ->
        val body = response.body()
        if (body == null) {
            val unexpectedNullBodyException = IllegalArgumentException(
                "Unexpected null response. Parametrize NetworkResponse with Void or Unit instead."
            )
            NetworkResponse.Failure.Unknown(unexpectedNullBodyException)
        } else {
            NetworkResponse.Success(
                response.code(),
                response.headers().toMultimap(),
                body
            )
        }
    }

    private val successMapper: (Response<Any?>) -> NetworkResponse<Any?> = when (type) {
        Void::class.java -> voidMapper
        Unit::class.java -> unitMapper
        else -> typeMapper
    }

    override fun responseType() = type

    override fun adapt(call: Call<Any?>) = NetworkResponseCall(call, successMapper)
}