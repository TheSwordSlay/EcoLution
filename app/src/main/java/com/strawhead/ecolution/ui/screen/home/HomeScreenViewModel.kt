package com.strawhead.ecolution.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.strawhead.ecolution.data.remote.response.GetAllHouseResponse
import com.strawhead.ecolution.data.remote.response.GetAllHouseResponseItem
import com.strawhead.ecolution.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenViewModel() : ViewModel() {
    private val _state = MutableStateFlow<List<GetAllHouseResponseItem>>(emptyList())
    val state = _state.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

//    init {
//
//        val client = ApiConfig.getApiService().getAllHouse()
//        client.enqueue(object : Callback<List<GetAllHouseResponseItem>> {
//            override fun onResponse(
//                call: Call<List<GetAllHouseResponseItem>>,
//                response: Response<List<GetAllHouseResponseItem>>
//            ) {
//                _loading.value = false
//                val responseBody = response.body()
//                Log.d("response body", responseBody.toString())
//                _state.value = responseBody!!
//                if (response.isSuccessful) {
//                }
//            }
//            override fun onFailure(call: Call<List<GetAllHouseResponseItem>>, t: Throwable) {
//                Log.e("error", "onFailure: ${t.message}")
//                _loading.value = false
//            }
//        })
//    }

    fun reload() {
        _loading.value = true
        val client = ApiConfig.getApiService().getAllHouse()
        client.enqueue(object : Callback<List<GetAllHouseResponseItem>> {
            override fun onResponse(
                call: Call<List<GetAllHouseResponseItem>>,
                response: Response<List<GetAllHouseResponseItem>>
            ) {
                _loading.value = false
                val responseBody = response.body()
                Log.d("response body", responseBody.toString())
                _state.value = responseBody!!
                if (response.isSuccessful) {
                }
            }
            override fun onFailure(call: Call<List<GetAllHouseResponseItem>>, t: Throwable) {
                Log.e("error", "onFailure: ${t.message}")
                _loading.value = false
            }
        })
    }
}