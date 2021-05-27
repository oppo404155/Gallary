package com.example.Gallary.ui

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.Gallary.data.UnsplashRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UnsplashViewModel @Inject constructor(
    private val unsplashRepo: UnsplashRepo
    ):ViewModel() {

   private val CurrentQuery=MutableLiveData("life")
   val photos=CurrentQuery.switchMap {
       unsplashRepo.getSearchedPhoto(it).cachedIn(viewModelScope)

   }
    fun searchphoto(query:String){
        CurrentQuery.value=query
    }
}