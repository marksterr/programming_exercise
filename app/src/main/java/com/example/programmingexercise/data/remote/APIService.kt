package com.example.programmingexercise.data.remote

import com.example.programmingexercise.data.remote.dtos.CountryDTO
import retrofit2.Response
import retrofit2.http.GET

/**
 * Service interface to define API endpoints.
 */
interface APIService {

    // Specify the endpoint to retrieve countries.
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): List<CountryDTO>
}