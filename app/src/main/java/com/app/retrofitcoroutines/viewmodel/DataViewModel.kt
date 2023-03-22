package com.app.retrofitcoroutines.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.app.retrofitcoroutines.data.remote.base.NetworkResult
import com.app.retrofitcoroutines.data.DataRepo
import kotlinx.coroutines.Dispatchers

class DataViewModel(private val dataRepo: DataRepo) : ViewModel() {

    private val TAG = "DataViewModel"

    // live data builder pattern
    // also can use viewModelScope.launch {  } and observe with mutablelivedata
    // https://developer.android.com/kotlin/coroutines

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(NetworkResult.Loading("Loading..."))
        when(val result = dataRepo.getUsers()){
            is NetworkResult.Success -> emit(NetworkResult.Success(data = result.data))
            is NetworkResult.Error -> emit(NetworkResult.Error(message = result.message))
            else -> emit(NetworkResult.Error("Invalid response state!!"))
        }
    }
}