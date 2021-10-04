package com.example.android.searchimage.detail

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.searchimage.network.ImageProperty

class ImageDetailsFactory(
    private val imageProperty: Array<ImageProperty>,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageDetailsViewModel::class.java)) {
            return ImageDetailsViewModel(imageProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
