package com.example.Gallary

import com.example.Gallary.api.UnsplashAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object HiltObject {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit=
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(UnsplashAPI.base_URL).build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): UnsplashAPI =retrofit.create(UnsplashAPI::class.java)


}