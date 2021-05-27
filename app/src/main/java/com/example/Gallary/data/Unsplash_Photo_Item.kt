package com.example.Gallary.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Unsplash_Photo_Item(
    val id: String,
    val description: String?,
    val user: UnsplashUser,
    val urls: UnsplashUrls
) : Parcelable {

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String,
        val portfolio_url:String

    ) : Parcelable

    @Parcelize
    data class UnsplashUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ) : Parcelable


}


