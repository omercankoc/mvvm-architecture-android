package com.omercankoc.mvvmarchitecture.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omercankoc.mvvmarchitecture.model.Country
import com.omercankoc.mvvmarchitecture.service.CountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    val countryMutableLiveData = MutableLiveData<List<Country>>()

    // CountryService nesnesini olustur.
    private val countryService = CountryService()
    // Yapilan Call'lar fragment veya activity sonladirildiginda sonlandir.
    private val disposable = CompositeDisposable()

    fun getFeedData(){
        getFeedDataFromAPI()
    }

    private fun getFeedDataFromAPI(){
        disposable.add(
            countryService.getCountry()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver <List<Country>>() {
                    // Basarili olursa
                    override fun onSuccess(t: List<Country>) {
                        countryMutableLiveData.value = t
                    }

                    // Hata alinirsa
                    override fun onError(e: Throwable) {

                    }
                })
        )
    }
}