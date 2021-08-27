package com.omercankoc.mvvmarchitecture.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omercankoc.mvvmarchitecture.model.Country
import com.omercankoc.mvvmarchitecture.service.CountryDatabase
import com.omercankoc.mvvmarchitecture.service.CountryService
import com.omercankoc.mvvmarchitecture.utility.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FeedViewModel(application : Application) : BaseViewModel(application) {

    val countryMutableLiveData = MutableLiveData<List<Country>>()

    // CountryService nesnesini olustur.
    private val countryService = CountryService()
    // Yapilan Call'lar fragment veya activity sonladirildiginda sonlandir.
    private val disposable = CompositeDisposable()
    //
    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    //
    private var refreshTime = 10 * 60 * 1000 * 1000 * 1000L

    //
    fun getFeedData(){
        // En son hangi zamanda
        val updateTime = customSharedPreferences.getTime()

        // En son guncelleme zamani 10dk ustunde ise Room'dan verileri cek.
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        }

        else {
            getFeedDataFromAPI()
        }
    }

    //
    private fun getDataFromSQLite() {
        launch {
            val list = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(list)
            Toast.makeText(getApplication(),"Countries From SQLite",Toast.LENGTH_LONG).show()
        }
    }

    //
    private fun getFeedDataFromAPI(){
        disposable.add(
            countryService.getCountry()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver <List<Country>>() {
                    // Basarili olursa
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    // Hata alinirsa
                    override fun onError(e: Throwable) {
                        Toast.makeText(getApplication(),"ERROR!",Toast.LENGTH_LONG).show()
                    }
                })
        )
    }

    //
    private fun showCountries(countryList : List<Country>){
        countryMutableLiveData.value = countryList
    }

    // Alinan verileri arka planda Room yardimi ile SQLite'a kaydet.
    private fun storeInSQLite(list : List<Country>){
        launch {
            // Veri Tabani DAO nesnesini tanimla
            val dao = CountryDatabase(getApplication()).countryDao()
            // Once tum verileri sil.
            dao.deleteAllCountries()
            // Tum verileri listeden individual'a cevirerek db'ye ekle.
            val listLong = dao.insertAll(*list.toTypedArray())
            var i = 0
            while(i < list.size){
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCountries(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }
}