package com.omercankoc.mvvmarchitecture.utility

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omercankoc.mvvmarchitecture.R

// Estension
fun ImageView.downloadFromUrl(url : String, progressDrawable: CircularProgressDrawable){

    // Yukleme gelene kadar veya basarisiz olmasi durumunda atanan ayarlari tanimla.
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

// Image yuklemesi yapilirken Progress Bar olustur.
fun placeholderProgressBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f // Genislik
        centerRadius = 40f // Yaricap
        start()
    }
}