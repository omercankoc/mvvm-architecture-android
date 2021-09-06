package com.omercankoc.mvvmarchitecture.service

import com.omercankoc.mvvmarchitecture.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {
    // https://raw.githubusercontent.com/ -> Base URL
    // omercankoc/mvvm-architecture-android/master/G20.json -> EXT
    @GET("omercankoc/mvvm-architecture-android/master/G20.json")
    // Single -> API'ye bir kere eris ve bekle.
    // Observable -> API'ye bir kere eris ve dinle.
    fun getCountryFromAPI() : Single<List<Country>>
}