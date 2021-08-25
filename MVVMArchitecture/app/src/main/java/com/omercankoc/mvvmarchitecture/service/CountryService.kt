package com.omercankoc.mvvmarchitecture.service

import com.omercankoc.mvvmarchitecture.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryService {
    // https://raw.githubusercontent.com/ -> Base URL
    // atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json -> EXT
    private val BASE_URL : String = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getCountry() : Single<List<Country>> {
        return api.getCountryFromAPI()
    }
}