package com.example.programmingexercise

import com.example.programmingexercise.data.CountryRepo
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.presentation.CountryViewModel
import com.example.programmingexercise.util.CoroutinesExtension
import com.example.programmingexercise.util.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

@ExtendWith(InstantTaskExecutorExtension::class)
internal class ViewModelTest {

    @RegisterExtension
    private val testExtension = CoroutinesExtension()
    private val repo: CountryRepo = mockk()
    private val viewModel = CountryViewModel(repo)

    @Test
    fun testGetCountries() = runTest(testExtension.testDispatcher) {
        val expectedResult = listOf(
            Country(
                code = "US",
                capital = "Washington, D.C.",
                name = "United States of America",
                region = "NA"
            )
        )
        coEvery { repo.getCountries() } coAnswers { expectedResult }
        viewModel.getCountries()
        val result = viewModel.countries.value
        Assertions.assertEquals(expectedResult, result)
    }
}