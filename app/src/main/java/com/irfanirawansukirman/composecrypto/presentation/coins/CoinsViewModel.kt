package com.irfanirawansukirman.composecrypto.presentation.coins

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.usecase.CoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinsUseCase: CoinsUseCase
) : ViewModel() {

    init {
        getCoins()
    }

    private val _state = mutableStateOf(CoinsState())
    val state: State<CoinsState> = _state

    fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase()
                .collect { state ->
                    withContext(Dispatchers.Main) {
                        when (state) {
                            is Resource.Loading -> _state.value =
                                CoinsState(isLoading = state.isLoading)
                            is Resource.Success -> _state.value = CoinsState(coins = state.data)
                            is Resource.Error -> _state.value = CoinsState(error = state.error)
                        }
                    }
                }
        }
    }
}