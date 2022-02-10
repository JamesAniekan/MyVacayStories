package com.android.example.myvacaystories.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.myvacaystories.model.AppRepository
import com.android.example.myvacaystories.model.StoryPost
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    private val appRepo = AppRepository()

    private val _postsLists = MutableLiveData<List<StoryPost>?>()

    val postLists: LiveData<List<StoryPost>?>
        get() = _postsLists

    init {
        getPosts()
    }

   fun getPosts(){
       viewModelScope.launch {
           _postsLists.value = appRepo.getPosts()

       }
   }

}