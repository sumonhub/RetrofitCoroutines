package com.mindorks.retrofit.coroutines.data.repository

import com.mindorks.retrofit.coroutines.data.api.ApiService

class MainRepository(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
}