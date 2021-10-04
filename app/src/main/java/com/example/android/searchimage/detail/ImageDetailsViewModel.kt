package com.example.android.searchimage.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.searchimage.network.ImageProperty

class ImageDetailsViewModel (imageProperty: ImageProperty, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<ImageProperty>()

    val selectedProperty: LiveData<ImageProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = imageProperty
    }
}