package com.omercankoc.mvvmarchitecture.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.omercankoc.mvvmarchitecture.adapter.CountryAdapter
import com.omercankoc.mvvmarchitecture.databinding.FragmentFeedBinding
import com.omercankoc.mvvmarchitecture.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    // Definition Binding
    private lateinit var binding : FragmentFeedBinding
    // Ilgili ViewModel nesnesini olustur.
    private lateinit var feedViewModel : FeedViewModel
    // Adapter nesnesi olustur.
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Binding
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View olusturulduktan sonra ViewModel ile Fragmani bagla.
        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        // View olusturulduktan sonra View Model verilerini cek.
        feedViewModel.getFeedData()
        // Itemleri dikey konumda goruntule.
        binding.recyclerViewCountries.layoutManager = LinearLayoutManager(context)
        // View ile adapter'i bagla.
        binding.recyclerViewCountries.adapter = countryAdapter

        // Swipe asagi dogru cekildiginde verileri API araciligi ile guncelle.
        binding.swipeRefreshLayout.setOnRefreshListener {
            feedViewModel.refreshFeed()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    // Feed verilerini dinle ve degisiklik oldugunda Adapter araciligi ile Recycler View'i yenile.
    private fun observeLiveData(){
        feedViewModel.countryMutableLiveData.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                countryAdapter.refreshCountryAdapter(countries)
            }
        })
    }
}