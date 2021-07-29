package com.omercankoc.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omercankoc.mvvm.model.Country

class FeedViewModel : ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val country1 = Country("bulgaria","sofia","europa","euro","bulgarian",".com")
        val country2 = Country("greek","atina","europa","euro","greek",".com")
        val country3 = Country("algeria","tiran","europa","euro","algerian",".com")

        val countryList = arrayListOf<Country>(country1,country2,country3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false

    }

}