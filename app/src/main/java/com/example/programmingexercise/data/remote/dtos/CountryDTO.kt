package com.example.programmingexercise.data.remote.dtos


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDTO(
    @SerialName("capital")
    val capital: String,
    @SerialName("code")
    val code: String,
    @SerialName("currency")
    val currency: CurrencyDTO?,
    @SerialName("demonym")
    val demonym: String?,
    @SerialName("flag")
    val flag: String?,
    @SerialName("language")
    val language: LanguageDTO?,
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String
)