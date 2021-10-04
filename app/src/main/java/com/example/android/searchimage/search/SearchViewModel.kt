package com.example.android.searchimage.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.SearchApi
import com.example.android.searchimage.network.ImageProperty
import com.example.android.searchimage.network.SearchResponseProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    companion object {
        private const val KEY = "18021445-326cf5bcd3658777a9d22df6f"
        private const val IMAGE_TYPE = "photo"
    }


    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _response = MutableLiveData<List<ImageProperty>>()
    val response: LiveData<List<ImageProperty>>
    get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    /**
     * Call getSearchRealEstateProperties() on init so we can display status immediately.
     */
    init {
    }


    /**
     * Sets the value of the response+
     */
     fun displayResponseNumber(query:String) {
        coroutineScope.launch {
            var getPropertiesDeferred = SearchApi.retrofitService.getImageDetails(KEY,query,"IMAGE_TYPE")
            try {
                var result = getPropertiesDeferred.await()
                _response.value = result.hits
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}