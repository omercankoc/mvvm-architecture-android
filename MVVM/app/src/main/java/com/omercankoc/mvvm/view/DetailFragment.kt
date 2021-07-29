package com.omercankoc.mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.omercankoc.mvvm.R
import com.omercankoc.mvvm.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var viewModel : DetailViewModel

    private var countryUUID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUUID = DetailFragmentArgs.fromBundle(it).countryUUID
        }

        observeLiveData()
    }

    private fun observeLiveData(){
         viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
             country?.let {
                 textViewName.text = country.country
                 textViewCapital.text = country.capital
                 textViewRegion.text = country.region
                 textViewCurrency.text = country.currency
                 textViewLanguage.text = country.language
             }
         })
    }
}