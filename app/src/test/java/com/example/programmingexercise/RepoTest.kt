package com.example.programmingexercise

import com.example.programmingexercise.data.CountryRepo
import com.example.programmingexercise.data.local.Country
import com.example.programmingexercise.data.remote.APIService
import com.example.programmingexercise.data.remote.ResultState
import com.example.programmingexercise.data.remote.dtos.CountryDTO
import com.example.programmingexercise.util.CoroutinesExtension
import io.mockk.mockk
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.io.IOException

internal class RepoTest {

    @RegisterExtension
    private val testExtension = CoroutinesExtension()
    private val service: APIService = mockk()
    private val repo = CountryRepo(service)

    @Test
    fun testGetCountries() = runTest(testExtension.testDispatcher) {
        val serviceResponse = listOf(
            CountryDTO(
                capital = "Washington, D.C.",
                code = "US",
                currency = null,
                demonym = "American",
                flag = "https://example.com/usa_flag.png",
                language = null,
                name = "United States of America",
                region = "NA"
            )
        )
        val expectedResult = ResultState.Success(
            listOf(
                Country(
                    code = "US",
                    capital = "Washington, D.C.",
                    name = "United States of America",
                    region = "NA"
                )
            )
        )

        coEvery { service.getCountries() } coAnswers { serviceResponse }

        val result = repo.getCountries()
        assertEquals(expectedResult, result)
    }

    @Test
    fun testGetCountriesError() = runTest(testExtension.testDispatcher) {
        val exceptionMessage = "Network error"
        val thrownException = IOException(exceptionMessage)

        // Mocking a thrown exception for service.getCountries()
        coEvery { service.getCountries() } coAnswers { throw thrownException }

        val result = repo.getCountries()

        // Check if the result is of type ResultState.Error
        assertTrue(result is ResultState.Error)

        // Cast the result to ResultState.Error and check if the exception message matches
        val errorResult = result as ResultState.Error
        assertEquals(exceptionMessage, errorResult.exception.message)
    }
}