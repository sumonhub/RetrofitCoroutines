package com.mindorks.retrofit.coroutines.data.api

import com.mindorks.retrofit.coroutines.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataService {

    // define all API end point here
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}