package com.android.example.myvacaystories.viewmodels

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.example.myvacaystories.model.AppRepository
import com.android.example.myvacaystories.model.NetworkStatus
import kotlinx.coroutines.launch

class NewPostViewModel: ViewModel(){
    private val appRepo = AppRepository()

    private val _networkState = MutableLiveData<NetworkStatus>()
    val networkState: LiveData<NetworkStatus>
        get() = _networkState

    fun newPost(description: String, imageUri: Uri){
        viewModelScope.launch {
            _networkState.value = NetworkStatus.LOADING
            _networkState.value = appRepo.newPost(description, imageUri)
        }
    }
}