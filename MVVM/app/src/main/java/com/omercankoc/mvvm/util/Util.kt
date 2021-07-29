package com.omercankoc.mvvm.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omercankoc.mvvm.R

fun ImageView.downloadFromURL(url : String?, progressDrawable: CircularProgressDrawable){

    val requestOptions = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(requestOptions)
        .load(url)
        .into(this)
}


fun placeholderProgressBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}