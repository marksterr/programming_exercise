package com.example.programmingexercise.data

import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.APIService
import com.example.programmingexercise.data.remote.ResultState
import com.example.programmingexercise.data.util.FailureResponse

/**
 * Repository class responsible for fetching country data.
 *
 * @param service The API service to retrieve data.
 */
class CountryRepo(private val service: APIService) {

    // Fetch countries from the API and map them to domain models.
    suspend fun getCountries(): ResultState<List<Country>> {
        return try {
            val countryDTOs = service.getCountries()
            if (countryDTOs.isNotEmpty()) {
                val countries = countryDTOs.map {
                    Country(
                        capital = it.capital,
                        code = it.code,
                        name = it.name,
                        region = it.region
                    )
                }
                ResultState.Success(countries)
            } else {
                throw FailureResponse("Failure to get response")
            }
        } catch (e: Exception) {
            // Handle possible network or conversion errors.
            ResultState.Error(e)
        }
    }
}