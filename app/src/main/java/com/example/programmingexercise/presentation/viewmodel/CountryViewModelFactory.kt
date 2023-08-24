package com.example.programmingexercise.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.programmingexercise.data.CountryRepo

/**
 * Factory class for creating [CountryViewModel] instances.
 *
 * @param repo The repository required for the ViewModel's constructor.
 */
class CountryViewModelFactory(private val repo: CountryRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ensure the ViewModel is of type CountryViewModel. Otherwise, throw an exception.
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}