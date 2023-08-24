package com.example.programmingexercise.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programmingexercise.data.CountryRepo
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.ResultState
import kotlinx.coroutines.launch

class CountryViewModel(private val repo: CountryRepo) : ViewModel() {
    private val _countries: MutableLiveData<ResultState<List<Country>>> = MutableLiveData()
    val countries: LiveData<ResultState<List<Country>>> get() = _countries

    init {
        getCountries()
    }

    fun getCountries() = viewModelScope.launch {
        _countries.value = ResultState.Loading
        _countries.value = repo.getCountries()
    }
}