package com.example.android.searchimage.detail

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.searchimage.network.ImageProperty

class ImageDetailsViewModel(imageProperty: Array<ImageProperty>, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<Array<ImageProperty>>()
    val selectedProperty: LiveData<Array<ImageProperty>>
        get() = _selectedProperty

    init {
        _selectedProperty.value = imageProperty
    }
}