package com.omercankoc.mvvmarchitecture.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omercankoc.mvvmarchitecture.model.Country

class FeedViewModel : ViewModel() {
    val countryMutableLiveData = MutableLiveData<List<Country>>()

    fun refreshFeedData(){
        val country1 = Country("Turkey","Ankara","Asia","TRL","Turkish","image")
        val country2 = Country("Greek","Atina","Europe","Euro","Greeks","image")
        val country3 = Country("Eygpt","Cahiro","Africa","MSL","Eyptian","image")

        val countryList = arrayListOf<Country>(country1,country2,country3)
        countryMutableLiveData.value = countryList
    }
}