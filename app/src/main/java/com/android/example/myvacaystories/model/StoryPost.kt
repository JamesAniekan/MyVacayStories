package com.android.example.myvacaystories.model

import com.google.firebase.firestore.Exclude

data class StoryPost(
    val id: String = "",
    val userId: String = "",
    val description: String = "",
    var creationTime: Long = 0,
    var imageUrl: String = "",
    @get:Exclude var userName: String = ""
)
