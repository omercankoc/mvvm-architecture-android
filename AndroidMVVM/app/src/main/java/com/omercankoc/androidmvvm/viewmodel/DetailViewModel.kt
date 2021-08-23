package com.omercankoc.androidmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omercankoc.androidmvvm.model.Country

class DetailViewModel : ViewModel() {
    var countryLiveData = MutableLiveData<Country>()

    fun getDetailsFromRoom(){
        var country = Country("Turkey","Ankara","Asia","TL","Turkish","image")
        countryLiveData.value = country
    }
}