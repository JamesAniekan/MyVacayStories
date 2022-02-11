package com.android.example.myvacaystories.adapters

import android.net.Uri
import android.text.format.DateUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.android.example.myvacaystories.model.StoryPost
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

@BindingAdapter("timeFormat")
fun TextView.timeFormatting(time: Long){
    text = DateUtils.getRelativeTimeSpanString(time)
}

fun ImageView.cropImage(src: Uri){

}