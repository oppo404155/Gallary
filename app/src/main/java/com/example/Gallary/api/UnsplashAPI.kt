package com.example.Gallary.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashAPI {
    companion object{
        const val base_URL="https://api.unsplash.com/"
        const val client_id="FY84NgzrOwshO0j2WlFXXmaTm0Y7A_9OQi51YChmkmQ"
    }
 @Headers("Accept-Version: v1", "Authorization: Client-ID $client_id")
    @GET("search/photos")
    suspend fun searchPhoto(
        @Query("query")query: String,
        @Query("page") page:Int,
        @Query("per_page") per_page:Int
    ):UnsplashResults


}
