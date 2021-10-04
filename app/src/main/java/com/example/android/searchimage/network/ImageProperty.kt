package com.example.android.searchimage.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageProperty (
        val id:Int,
        val previewURL:String,
        val user:String
):Parcelable{

}