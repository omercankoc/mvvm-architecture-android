package com.omercankoc.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omercankoc.countries.R
import com.omercankoc.countries.model.Country
import kotlinx.android.synthetic.main.fragment_country.view.*
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){

    class CountryViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

    }

    // View'i Holder'a bagla.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    // Veri setini Holder`a bagla.
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // item_country nesnelerini bagla.
        holder.view.textViewCountryName.text = countryList[position].countryName
        holder.view.textViewRagion.text = countryList[position].countryRegion
    }

    // Dondurulecek item sayisini belirt.
    override fun getItemCount(): Int {
        return countryList.size
    }

    // Kullanici RecylerView'i asagi cecktiginde guncelleme yap.
    fun updateCountyList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged() // Adapter'i yenile.
    }
}