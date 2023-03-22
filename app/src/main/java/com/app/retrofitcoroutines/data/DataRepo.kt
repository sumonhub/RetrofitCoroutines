package com.app.retrofitcoroutines.data

import com.app.retrofitcoroutines.data.remote.base.BaseApiResponse
import com.app.retrofitcoroutines.data.remote.base.NetworkResult
import com.mindorks.retrofit.coroutines.data.api.RetrofitBuilder
import com.mindorks.retrofit.coroutines.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DataRepo : BaseApiResponse() {

    private val TAG = "DataRepo"

    suspend fun getUsers(): NetworkResult<List<User>> {
        // main-safety : Move the execution of the coroutine to the I/O dispatcher
        return withContext(Dispatchers.IO) {
            //
            safeApiCall { RetrofitBuilder.getRemoteDataService().getUsers() }
        }
    }
}