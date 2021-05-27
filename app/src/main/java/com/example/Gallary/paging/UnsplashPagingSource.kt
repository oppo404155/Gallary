package com.example.Gallary.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.example.Gallary.api.UnsplashAPI
import com.example.Gallary.data.Unsplash_Photo_Item
import java.io.IOException
import javax.inject.Inject

class UnsplashPagingSource @Inject constructor(
    val unsplashAPI: UnsplashAPI,
    val query: String
) : PagingSource<Int, Unsplash_Photo_Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Unsplash_Photo_Item> {

        return try {
            val position=params.key?:1
            val photos=unsplashAPI.searchPhoto(query,position,params.loadSize).results
           val pkey=if (position==1)null else position-1
            val nKey=if (photos.isEmpty())null else position+1
            LoadResult.Page(
                data = photos,
                nextKey = nKey,
                prevKey = pkey
                )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Unsplash_Photo_Item>): Int? {
        TODO("Not yet implemented")
    }


}