package com.app.retrofitcoroutines.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.retrofitcoroutines.data.remote.base.NetworkResult
import com.app.retrofitcoroutines.data.DataRepo
import com.app.retrofitcoroutines.databinding.ActivityMainBinding
import com.app.retrofitcoroutines.ui.adapter.MainAdapter
import com.app.retrofitcoroutines.viewmodel.DataViewModel
import com.mindorks.retrofit.coroutines.data.model.User

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        fetchData()
    }

    private fun fetchData() {
        val dataViewModel = DataViewModel(DataRepo())
        dataViewModel.getUsers().observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.d(TAG, "fetchData: Success...")
                    updateList(response.data)
                }
                is NetworkResult.Error -> {
                    // show error message
                    Log.d(TAG, "fetchData: ${response.message}")
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                    Log.d(TAG, "fetchData: Loading...")
                }
            }
        }
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun updateList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}
