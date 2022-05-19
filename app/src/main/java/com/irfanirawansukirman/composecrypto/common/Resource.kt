package com.irfanirawansukirman.composecrypto.common

sealed class Resource<out T : Any?> {
    data class Success<out T : Any?>(val data: T?) : Resource<T>()
    data class Error(val error: String) : Resource<Nothing>()
    data class Loading(val isLoading: Boolean) : Resource<Nothing>()
}