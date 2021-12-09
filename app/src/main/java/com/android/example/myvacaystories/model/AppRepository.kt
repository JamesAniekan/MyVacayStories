package com.android.example.myvacaystories.model

import android.app.Application
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class AppRepository(private val application: Application) {

    private val userAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private  val userObj = FirebaseFirestore.getInstance().collection("users")

      fun registerUser(name: String, email:String, password: String){
        if(email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch{
                try {
                   val regUser = userAuth.createUserWithEmailAndPassword(email, password).await()
                    //Check if AuthResult user is not null and get the id
                    val userId = regUser.user?.uid!!
                    val user = User(userId, name)
                    userObj.document(userId).set(user).await()
                    withContext(Dispatchers.Main){
                        Toast.makeText(application.applicationContext, "Registered successfully", Toast.LENGTH_LONG)
                            .show()

                     }
                }
                catch (e: Exception){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(application.applicationContext, e.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    fun signInUser(email: String, password:String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    userAuth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main){
                        Toast.makeText(application.applicationContext, "Signed in successfully", Toast.LENGTH_LONG)
                            .show()
                    }
                }catch (e: Exception){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(application.applicationContext, e.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }

    }
}








