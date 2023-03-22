package com.app.retrofitcoroutines.data.remote.base

import android.util.Log
import retrofit2.Response

abstract class BaseApiResponse {
    private val TAG = "BaseApiResponse"

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(data = body)
                }
            }
            return NetworkResult.Error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.d(TAG, "safeApiCall: ${e.message ?: e.toString()}")
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
}