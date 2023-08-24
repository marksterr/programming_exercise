package com.example.programmingexercise

import com.example.programmingexercise.data.CountryRepo
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.ResultState
import com.example.programmingexercise.presentation.viewmodel.CountryViewModel
import com.example.programmingexercise.util.CoroutinesExtension
import com.example.programmingexercise.util.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import java.io.IOException

@ExtendWith(InstantTaskExecutorExtension::class)
internal class ViewModelTest {

    @RegisterExtension
    private val testExtension = CoroutinesExtension()
    private val repo: CountryRepo = mockk(relaxed = true)
    private lateinit var viewModel: CountryViewModel

    @BeforeEach
    fun setup() {
        viewModel = CountryViewModel(repo)
    }
    @Test
    fun testGetCountries() = runTest(testExtension.testDispatcher) {
        val expectedData = listOf(
            Country(
                code = "US",
                capital = "Washington, D.C.",
                name = "United States of America",
                region = "NA"
            )
        )
        val successResult = ResultState.Success(expectedData)
        coEvery { repo.getCountries() } coAnswers { successResult }

        viewModel.getCountries()

        val result = viewModel.countries.value

        assertTrue(result is ResultState.Success)
        assertEquals(expectedData, (result as ResultState.Success).data)
    }

    @Test
    fun testGetCountriesLoadingState() = runTest(testExtension.testDispatcher) {
        coEvery { repo.getCountries() } coAnswers { delay(10); ResultState.Success(emptyList()) }

        viewModel.getCountries()

        val loadingState = viewModel.countries.value
        assertTrue(loadingState is ResultState.Loading)
    }

    @Test
    fun testGetCountriesError() = runTest(testExtension.testDispatcher) {
        val exceptionMessage = "Network error"
        val thrownException = IOException(exceptionMessage)

        coEvery { repo.getCountries() } coAnswers { throw thrownException }

        viewModel.getCountries()

        val result = viewModel.countries.value
        assertTrue(result is ResultState.Error)
        assertEquals(exceptionMessage, (result as ResultState.Error).exception.message)
    }

    @Test
    fun testViewModelInitializationFetchesCountries() = runTest(testExtension.testDispatcher) {
        val expectedData = listOf(
            Country(
                code = "US",
                capital = "Washington, D.C.",
                name = "United States of America",
                region = "NA"
            )
        )
        val successResult = ResultState.Success(expectedData)
        coEvery { repo.getCountries() } coAnswers { successResult }

        // Just initializing the ViewModel should trigger the data fetch.
        val newViewModel = CountryViewModel(repo)

        val result = newViewModel.countries.value
        assertTrue(result is ResultState.Success)
        assertEquals(expectedData, (result as ResultState.Success).data)
    }
}