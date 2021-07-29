package com.omercankoc.mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omercankoc.mvvm.R
import com.omercankoc.mvvm.model.Country
import com.omercankoc.mvvm.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.recycler_item.view.*

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(val view : View) : RecyclerView.ViewHolder(view) { }

    // View ile Adapter'i birbirine bagla. ( recyler_item ile CountryAdapter )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_item,parent,false)
        return CountryViewHolder(view)
    }

    // Ilgili view'lar ile ilgili verileri bagla.
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.textViewCountryName.text = countryList[position].country
        holder.view.textViewCountryRegion.text = countryList[position].region

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(

            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    // Olusturulacak satir sayisini elde et ve dondur.
    override fun getItemCount(): Int {
       return countryList.size
    }

    // Swipe ile verileri guncelle.
    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged() // Adapteri yenile.
    }
}