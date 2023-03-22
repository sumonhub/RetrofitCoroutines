package com.app.retrofitcoroutines.data.remote.base

sealed class NetworkResult<out R> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
    data class Loading(val message: String) : NetworkResult<Nothing>()
}