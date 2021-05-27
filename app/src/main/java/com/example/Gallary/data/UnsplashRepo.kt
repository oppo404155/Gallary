package com.example.Gallary.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.Gallary.api.UnsplashAPI
import com.example.Gallary.paging.UnsplashPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepo @Inject constructor(private val usplshApi:UnsplashAPI) {

    fun getSearchedPhoto(query:String)=
        Pager(
            config = PagingConfig(
                pageSize = 20 ,
                maxSize = 100 ,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {UnsplashPagingSource(unsplashAPI = usplshApi,query = query)}
        ).liveData


}