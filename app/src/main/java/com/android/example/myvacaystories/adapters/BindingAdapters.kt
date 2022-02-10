package com.android.example.myvacaystories.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imgUrl")
fun bindImage(img: ImageView, urlToImage: String){
    urlToImage.let {
        val imgUri = urlToImage.toUri().buildUpon().scheme("https").build()

        Glide.with(img.context)
            .load(imgUri)
            .into(img)
    }
}