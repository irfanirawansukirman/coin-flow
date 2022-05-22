package com.irfanirawansukirman.composecrypto.common

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

sealed class Resource<out T : Any?> {

    data class Loading(val isLoading: Boolean) : Resource<Nothing>()
    data class Success<out T : Any?>(val data: T?) : Resource<T>()
    data class Error(val error: String) : Resource<Nothing>()
}

@ExperimentalCoroutinesApi
suspend fun <T> executeFlowProcess(
    coroutineContextProvider: CoroutineContextProvider,
    function: suspend () -> T
): Flow<Resource<T>> {
    return flow<Resource<T>> { emit(Resource.Success(function())) }
        .catch { emit(Resource.Error(it.localizedMessage ?: "Something went wrong")) }
        .onStart { emit(Resource.Loading(true)) }
        .flowOn(coroutineContextProvider.io)
}