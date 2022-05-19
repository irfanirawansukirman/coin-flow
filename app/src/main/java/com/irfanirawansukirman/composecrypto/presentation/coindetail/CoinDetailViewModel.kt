package com.irfanirawansukirman.composecrypto.presentation.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfanirawansukirman.composecrypto.common.Constants
import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.usecase.CoinByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinByIdUseCase: CoinByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<String>(Constants.COIN_ID)?.let { coinId -> getCoinById(coinId) }
    }

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    fun getCoinById(coinId: String) {
        viewModelScope.launch {
            getCoinByIdUseCase(coinId).collect { state ->
                when (state) {
                    is Resource.Loading -> _state.value =
                        CoinDetailState(isLoading = state.isLoading)
                    is Resource.Success -> _state.value = CoinDetailState(coins = state.data)
                    is Resource.Error -> _state.value = CoinDetailState(error = state.error)
                }
            }
        }
    }
}