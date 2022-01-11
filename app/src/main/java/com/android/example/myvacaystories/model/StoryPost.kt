package com.android.example.myvacaystories.model

data class StoryPost(
    val id: String = "",
    val userId: String = "",
    val description: String = "",
    var creationTime: Long = 0,
    var imageUrl: String = "",
    )
