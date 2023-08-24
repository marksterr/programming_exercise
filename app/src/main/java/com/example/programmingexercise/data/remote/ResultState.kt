package com.example.programmingexercise.data.remote

/**
 * A sealed class representing the result of a given operation, encapsulating
 * the three possible states: success, error, and loading.
 *
 * @param T The type of the data that will be provided in case of success.
 */
sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val exception: Throwable) : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
}