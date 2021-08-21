package com.omercankoc.androidmvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.omercankoc.androidmvvm.adapter.CountryAdapter
import com.omercankoc.androidmvvm.databinding.FragmentFeedBinding
import com.omercankoc.androidmvvm.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    // Definition Binding
    private lateinit var binding : FragmentFeedBinding

    private lateinit var feedViewModel : FeedViewModel
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

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        feedViewModel.refreshFeedData()

        binding.recyclerViewCountries.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCountries.adapter = countryAdapter

        observeLiveData()
    }

    fun observeLiveData(){
        feedViewModel.countryMutableLiveData.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                countryAdapter.refresh(countries)
            }
        })
    }
}