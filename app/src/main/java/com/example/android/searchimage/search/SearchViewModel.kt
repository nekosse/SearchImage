package com.example.android.searchimage.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.SearchApi
import com.example.android.searchimage.network.imageProperty
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
     * Sets the value of the response
     */
    private fun getSearchRealEstateProperties() {
        SearchApi.retrofitService.getImageDetails("18021445-326cf5bcd3658777a9d22df6f","yellow","photo").enqueue( object: Callback<imageProperty> {
            override fun onFailure(call: Call<imageProperty>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<imageProperty>, response: Response<imageProperty>) {
                _response.value = "Success: ${response.body()?.total.toString()} Image retrieved"
            }
        })

    }

}