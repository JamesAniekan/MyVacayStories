package com.android.example.myvacaystories.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.android.example.myvacaystories.model.AppRepository

class SignInRegisterViewModel(application: Application): AndroidViewModel(application) {

private val appRepo = AppRepository(application)

    fun register(name: String, email: String, password: String){
        appRepo.registerUser(name, email, password)
    }

    fun signInUser(email: String, password: String){
        appRepo.signInUser(email, password)
    }

}