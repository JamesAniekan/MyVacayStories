package com.android.example.myvacaystories.model

import android.app.Application
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.Exception

class AppRepository() {

    private val storage = FirebaseStorage.getInstance()
    private val userAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val userObj = FirebaseFirestore.getInstance().collection("users")
    private val storyPosts = FirebaseFirestore.getInstance().collection("post")


    suspend fun registerUser(name: String, email: String, password: String): AuthResult? {
        return withContext(Dispatchers.IO) {
           try {
                val regUser = userAuth.createUserWithEmailAndPassword(email, password).await()
                //Check if AuthResult user is not null and get the id
                val userId = regUser.user?.uid!!
                val user = User(userId, name)
                userObj.document(userId).set(user).await()
                regUser
                /*withContext(Dispatchers.Main){
                        Toast.makeText(application.applicationContext, "Registered successfully", Toast.LENGTH_LONG)
                            .show()}*/
            } catch (e: Exception) {
                null
                /*withContext(Dispatchers.Main) {
                        Toast.makeText(application.applicationContext, e.message, Toast.LENGTH_LONG)
                            .show()*/
            }
        }
    }


    suspend fun signInUser(email: String, password: String): AuthResult? {
        return withContext(Dispatchers.IO) {
            try {
                userAuth.signInWithEmailAndPassword(email, password).await()
                /*withContext(Dispatchers.Main){
                        Toast.makeText(application.applicationContext, "Signed in successfully", Toast.LENGTH_LONG)
                            .show()
                    }*/
            } catch (e: Exception) {
                null

                //withContext(Dispatchers.Main) {
                //   Toast.makeText(application.applicationContext, e.message, Toast.LENGTH_LONG)
                //   .show()
            }
        }
    }

    suspend fun newPost(description: String, imageUri: Uri): NetworkStatus {
        return withContext(Dispatchers.IO) {
            try {
                val userId = userAuth.uid!!
                val postId = UUID.randomUUID().toString()
                //Upload the image Uri to Storage
                val uploadImageResult = storage.getReference(postId).putFile(imageUri).await()
                //Extract the image Url from Firebase storage
                val imageUrl =
                    uploadImageResult?.metadata?.reference?.downloadUrl?.await().toString()

                //Create post object
                val post = StoryPost(
                    postId,
                    userId,
                    description,
                    System.currentTimeMillis(),
                    imageUrl
                )
                //Finally create posts collection
                storyPosts.document(postId).set(post).await()
                NetworkStatus.DONE
            } catch (e: Exception) {
                NetworkStatus.ERROR
            }
        }
    }

    suspend fun getPosts(): List<StoryPost>? {
        return withContext(Dispatchers.IO) {
            try {
                lateinit var posts: List<StoryPost>
                val postLists = storyPosts
                    .orderBy("creationTime", Query.Direction.DESCENDING)

                //Get results and convert StoryPost object instances
                postLists.addSnapshotListener { snapShot, error ->
                    if (error != null || snapShot == null) {
                        Log.i("xxy", "this is my error msg ${error.toString()}")
                        return@addSnapshotListener

                    }
                    posts = snapShot.toObjects(StoryPost::class.java)

                }
                    posts.onEach {
                        val user = getUser(it.userId)
                        if (user != null) {
                            it.userName = user.name
                        }
                    }
                for(post in posts){
                    Log.i("Posts", "$post")
                }
                posts
            } catch (e: Exception) {
                null
            }

        }
    }

    /*
    Get a User.
     */
   private suspend fun getUser(uid: String): User? {
        return withContext(Dispatchers.IO) {
            try {
                val user = userObj.document(uid).get().await().toObject(User::class.java)
                user
            } catch (e: Exception) {
                    null
            }
        }
    }
}














