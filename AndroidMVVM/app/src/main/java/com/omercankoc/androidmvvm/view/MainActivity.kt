package com.omercankoc.androidmvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omercankoc.androidmvvm.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}