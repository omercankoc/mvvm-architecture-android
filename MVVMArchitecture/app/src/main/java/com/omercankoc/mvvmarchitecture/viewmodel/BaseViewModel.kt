package com.omercankoc.mvvmarchitecture.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application : Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()

    // Oncelikle isini yap sonra main thread'e don.
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    // App kapatildiginda job'u iptal et.
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}