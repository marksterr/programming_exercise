package com.example.programmingexercise.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.databinding.ItemCountryBinding

/**
 * Adapter for displaying a list of countries in a RecyclerView.
 */
class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    // List of countries to be displayed
    private var countryList: List<Country> = emptyList()

    /**
     * ViewHolder class that represents a single item in the RecyclerView.
     * @param binding The view binding for the country item layout.
     */
    inner class CountryViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

        // Populates the item view with the details of a country.
        fun displayCountry(country: Country) = with(binding) {
            // Set country name, region, and code to the main TextView
            tvCountryDetails.text = "${country.name}, ${country.region}     ${country.code}"

            // Set capital to the secondary TextView
            tvCountryCapital.text = country.capital
        }
    }

    // Called when the RecyclerView needs a new ViewHolder to represent an item.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        // Inflate the item view using the view binding
        val inflater = LayoutInflater.from(parent.context)
        return CountryViewHolder(
            ItemCountryBinding.inflate(inflater, parent, false)
        )
    }

    // Called by the RecyclerView to display the data at a specified position.
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // Bind the country data to the ViewHolder
        holder.displayCountry(countryList[position])
    }


    // Returns the number of items in the list.
    override fun getItemCount(): Int {
        return countryList.size
    }


    // Updates the list of countries and notifies the adapter of data change.
    fun setData(countries: List<Country>) {
        this.countryList = countries
        notifyDataSetChanged()
    }
}