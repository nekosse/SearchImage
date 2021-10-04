package com.example.android.searchimage.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.SearchApi
import com.example.android.searchimage.network.SearchResponseProperty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    companion object {
        private const val KEY = "18021445-326cf5bcd3658777a9d22df6f"
    }


    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
    get() = _response
    /**
     * Call getSearchRealEstateProperties() on init so we can display status immediately.
     */
    init {
    }


    /**
     * Sets the value of the response
     */
     fun displayResponseNumber(query:String) {
        SearchApi.retrofitService.getImageDetails("${KEY}",query,"photo").enqueue( object: Callback<SearchResponseProperty> {
            override fun onFailure(call: Call<SearchResponseProperty>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<SearchResponseProperty>, response: Response<SearchResponseProperty>) {
                _response.value = "Success: ${response.body()?.total.toString()} Image retrieved"
            }
        })

    }


}