package com.example.android.searchimage.network

import com.squareup.moshi.Json

data class SearchResponseProperty(
     val total: Int,
     val totalHits:Int,
     val hits:List<ImageProperty>
)
