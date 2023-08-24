package com.example.programmingexercise.data

import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.APIService

class CountryRepo(private val service: APIService) {

    suspend fun getCountries(): List<Country> {
        val countryDTOs = service.getCountries()
        return countryDTOs.map {
            Country(
                capital = it.capital,
                code = it.code,
                name = it.name,
                region = it.region
            )
        }
    }
}