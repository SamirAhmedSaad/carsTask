package com.g22solutions.carsapp.base

sealed class NetworkResult<out T:Any?> {

    data class Success<T: Any?>(val data : T?) : NetworkResult<T>()

    data class Error(val exception: Exception) : NetworkResult<Nothing>()
}