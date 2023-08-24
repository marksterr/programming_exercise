package com.example.programmingexercise.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.programmingexercise.R
import com.example.programmingexercise.data.CountryRepo
import com.example.programmingexercise.data.remote.NetworkProvider
import com.example.programmingexercise.databinding.FragmentCountryBinding

class CountryFragment : Fragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CountryViewModel by viewModels {
        CountryViewModelFactory(CountryRepo(NetworkProvider.apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCountryBinding.inflate(inflater, container, false).apply {
            _binding = this
            val adapter = CountryAdapter()
            viewModel.countries.observe(
                viewLifecycleOwner,
                Observer {
                    adapter.setData(it)
                }
            )
            binding.rvCountries.adapter = adapter
            binding.rvCountries.layoutManager = LinearLayoutManager(requireContext())
        }.root
    }
}