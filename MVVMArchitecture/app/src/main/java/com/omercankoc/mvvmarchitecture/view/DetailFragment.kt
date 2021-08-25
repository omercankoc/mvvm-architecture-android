package com.omercankoc.mvvmarchitecture.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.omercankoc.mvvmarchitecture.R
import com.omercankoc.mvvmarchitecture.databinding.FragmentDetailBinding
import com.omercankoc.mvvmarchitecture.viewmodel.DetailViewModel

class DetailFragment : Fragment() {

    // Definition Binding
    private lateinit var binding : FragmentDetailBinding
    // Ilgili ViewModel nesnesini olustur.
    private lateinit var detailViewModel : DetailViewModel

    private var countryUUID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Binding
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View olusturulduktan sonra ViewModel ile Fragmani bagla.
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        // View olusturulduktan sonra View Model verilerini Room'dan cek.
        detailViewModel.getDetailsFromRoom()
        // View olusturuldugunda Country UUID verisini bos degil ise degerini al.
        arguments?.let {
            countryUUID = DetailFragmentArgs.fromBundle(it).countryUUID
        }

        observeLiveData()
    }

    // Dinlenen canli verileri View Model'den al ve ilgili nesnelere aktar.
    private fun observeLiveData(){
        detailViewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.textViewCountry.text = country.country
                binding.textViewCapital.text = country.capital
                binding.textViewRegion.text = country.region
                binding.textViewCurrency.text = country.currency
                binding.textViewLanguage.text = country.language
            }
        })
    }
}