package com.example.fetchcodechallenge.utils

sealed class ResultInfo<T>(
    val data: T? = null,
    val error: String = ""
) {
    class Loading<T>(): ResultInfo<T>()
    class Success<T>(data: T?): ResultInfo<T>(data)
    class Error<T>(errorMessage: String):ResultInfo<T>(error = errorMessage)
}
