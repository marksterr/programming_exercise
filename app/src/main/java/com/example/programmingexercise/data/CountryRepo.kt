package com.example.programmingexercise.data

import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.APIService
import com.example.programmingexercise.data.remote.ResultState

class CountryRepo(private val service: APIService) {

    suspend fun getCountries(): ResultState<List<Country>> {
        return try {
            val countryDTOs = service.getCountries()
            val countries = countryDTOs.map {
                Country(
                    capital = it.capital,
                    code = it.code,
                    name = it.name,
                    region = it.region
                )
            }
            ResultState.Success(countries)
        } catch (e: Exception) {
            ResultState.Error(e)
        }
    }
}