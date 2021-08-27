package com.omercankoc.mvvmarchitecture.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omercankoc.mvvmarchitecture.model.Country
import com.omercankoc.mvvmarchitecture.service.CountryDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application : Application) : BaseViewModel(application){
    var countryLiveData = MutableLiveData<Country>()

    fun getDetailsFromRoom(uuid : Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}