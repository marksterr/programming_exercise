package com.example.programmingexercise.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.databinding.ItemCountryBinding

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var countryList: List<Country> = emptyList()

    inner class CountryViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun displayCountry(country: Country) = with(binding) {
            tvCountryDetails.text = "${country.name}, ${country.region}     ${country.code}"
            tvCountryCapital.text = country.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(
            ItemCountryBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.displayCountry(countryList[position])
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun setData(countries: List<Country>) {
        this.countryList = countries
        notifyDataSetChanged()
    }
}