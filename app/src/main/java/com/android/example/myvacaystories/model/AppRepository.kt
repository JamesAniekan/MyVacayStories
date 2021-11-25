package com.android.example.myvacaystories.model

import android.app.Application
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class AppRepository(private val application: Application) {

    private val userAuth: FirebaseAuth = FirebaseAuth.getInstance()


    fun registerUser(email:String, password: String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch{
                try {
                    userAuth.createUserWithEmailAndPassword(email, password)
                    withContext(Dispatchers.Main){
                        Toast.makeText(application.applicationContext,"Signed in successfully",Toast.LENGTH_LONG)
                            .show()
                    }
                }
                catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(application.applicationContext, e.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

}
