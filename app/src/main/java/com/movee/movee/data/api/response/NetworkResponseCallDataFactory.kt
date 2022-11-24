package com.movee.movee.data.api.response

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

object NetworkResponseCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? = getRawType(returnType)
        .takeIf { rawType -> rawType == Call::class.java }
        ?.let { getParameterUpperBound(0, returnType as ParameterizedType) }
        ?.takeIf { callType -> getRawType(callType) == NetworkResponse::class.java }
        ?.let { callType -> getParameterUpperBound(0, callType as ParameterizedType) }
        ?.let(::NetworkResponseAdapter)
}