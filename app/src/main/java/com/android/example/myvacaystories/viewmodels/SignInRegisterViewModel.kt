package com.android.example.myvacaystories.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.example.myvacaystories.model.AppRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.launch

class SignInRegisterViewModel(application: Application): AndroidViewModel(application) {

    private val appRepo = AppRepository()

    private val _signedInRegUser = MutableLiveData<AuthResult?>()
    val signedInRegUser: LiveData<AuthResult?>
            get() = _signedInRegUser

    fun register(name: String, email: String, password: String){
        viewModelScope.launch {
            _signedInRegUser.value = appRepo.registerUser(name, email, password)
        }
    }

   fun signInUser(email: String, password: String) {
       viewModelScope.launch {
           _signedInRegUser.value = appRepo.signInUser(email, password)
       }
   }

    fun onSignInCompleted(){
        _signedInRegUser.value = null
    }

}