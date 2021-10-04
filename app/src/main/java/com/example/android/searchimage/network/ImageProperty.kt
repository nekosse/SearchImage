package com.example.android.searchimage.network

import com.squareup.moshi.Json

data class ImageProperty (
        val id:Int,
        val previewURL:String,
        val user:String
)