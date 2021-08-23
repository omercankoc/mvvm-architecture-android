package com.omercankoc.mvvmarchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omercankoc.mvvmarchitecture.databinding.RecyclerViewRowBinding
import com.omercankoc.mvvmarchitecture.model.Country
import com.omercankoc.mvvmarchitecture.view.FeedFragmentDirections

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    // Ilgili view'in tutucu nesnesi.
    class CountryViewHolder(val binding: RecyclerViewRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // View holder olusturuldugunda layout'un view'larini bagla.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding =
            RecyclerViewRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    // Baglandiktan sonra ilgili view'a ilgili data'yi aktar.
    override fun onBindViewHolder(viewHolder: CountryViewHolder, position: Int) {
        viewHolder.binding.textViewFeedCountry.text = countryList[position].country
        viewHolder.binding.textViewFeedCapital.text = countryList[position].capital
        // Item'e tiklandiginda ilgili verinin detay sayfasina git.
        viewHolder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    // Olusturulacak recycler view sayisini elde et.
    override fun getItemCount(): Int {
        return countryList.size
    }

    fun refreshCountryAdapter(refreshCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(refreshCountryList)
        notifyDataSetChanged()
    }
}