package com.omercankoc.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omercankoc.mvvm.model.Country

class DetailViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country1 = Country("bulgaria","sofia","europa","euro","bulgarian",".com")
        countryLiveData.value = country1
    }

}