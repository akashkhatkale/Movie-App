package com.movee.movee.data.repository

import android.util.Log
import com.movee.movee.commons.exceptions.NoInternetConnectionException
import com.movee.movee.commons.exceptions.UnknownException
import com.movee.movee.data.api.Resource
import com.movee.movee.domain.mapper.Mapper
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    open suspend fun <T, S> safeApiCall(api: suspend () -> Response<T>, mapper: Mapper<T, S>): Resource<S> {
        try {
            val result = api()
            if (result.isSuccessful) {
                result.body()?.let { body ->
                    return Resource.Success(mapper.map(body))
                }
            }
            return Resource.Error(UnknownException())
        } catch (e: IOException) {
            return Resource.Error(NoInternetConnectionException())
        } catch (e: HttpException) {
            return Resource.Error(e)
        }
    }

}