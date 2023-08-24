package com.example.programmingexercise.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.programmingexercise.data.CountryRepo

class CountryViewModelFactory(private val repo: CountryRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}