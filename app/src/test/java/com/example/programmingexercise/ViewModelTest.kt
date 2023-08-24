package com.example.programmingexercise

import com.example.programmingexercise.data.CountryRepo
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.ResultState
import com.example.programmingexercise.presentation.viewmodel.CountryViewModel
import com.example.programmingexercise.util.CoroutinesExtension
import com.example.programmingexercise.util.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@ExtendWith(InstantTaskExecutorExtension::class)
internal class ViewModelTest {

    @RegisterExtension
    private val testExtension = CoroutinesExtension()
    private val repo: CountryRepo = mockk(relaxed = true)
    private val viewModel = CountryViewModel(repo)

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
}