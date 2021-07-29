package com.omercankoc.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.omercankoc.mvvm.R
import com.omercankoc.mvvm.adapter.CountryAdapter
import com.omercankoc.mvvm.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private var countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View ile ViewModel'i birbirine bagla.
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        recyclerViewFeed.layoutManager = LinearLayoutManager(context)
        recyclerViewFeed.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            recyclerViewFeed.visibility = View.GONE
            textViewFeed.visibility = View.GONE
            progressBarFeed.visibility = View.VISIBLE

            viewModel.refreshData()

            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                recyclerViewFeed.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if(it){
                    textViewFeed.visibility = View.VISIBLE
                } else {
                    textViewFeed.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if(it){
                    progressBarFeed.visibility = View.VISIBLE
                    recyclerViewFeed.visibility = View.GONE
                    textViewFeed.visibility = View.GONE
                } else {
                    progressBarFeed.visibility = View.GONE
                }
            }
        })
    }

}