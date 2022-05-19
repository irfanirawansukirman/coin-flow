package com.irfanirawansukirman.composecrypto.presentation.coins

import com.irfanirawansukirman.composecrypto.domain.model.Coins

data class CoinsState(
    val isLoading: Boolean = false,
    val coins: List<Coins>? = emptyList(),
    val error: String = ""
)
