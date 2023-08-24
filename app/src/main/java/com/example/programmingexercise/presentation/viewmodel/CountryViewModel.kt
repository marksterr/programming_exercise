package com.example.programmingexercise.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingexercise.data.CountryRepo
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.ResultState
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for providing country data to the UI.
 *
 * @param repo The repository to fetch country data.
 */
class CountryViewModel(private val repo: CountryRepo) : ViewModel() {
    private val _countries: MutableLiveData<ResultState<List<Country>>> = MutableLiveData()
    val countries: LiveData<ResultState<List<Country>>> get() = _countries

    init {
        getCountries()
    }

    // Fetch countries from the repo and update LiveData.
    fun getCountries() = viewModelScope.launch {
        _countries.value = ResultState.Loading
        try {
            _countries.value = repo.getCountries()
        } catch (e: Exception) {
            _countries.value = ResultState.Error(e)
        }
    }
}