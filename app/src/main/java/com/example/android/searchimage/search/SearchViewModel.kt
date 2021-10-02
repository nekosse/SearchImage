package com.example.android.searchimage.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.SearchApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
    get() = _response
    /**
     * Call getSearchRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getSearchRealEstateProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    private fun getSearchRealEstateProperties() {
        SearchApi.retrofitService.getProperties().enqueue( object: Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }
        })

    }

}