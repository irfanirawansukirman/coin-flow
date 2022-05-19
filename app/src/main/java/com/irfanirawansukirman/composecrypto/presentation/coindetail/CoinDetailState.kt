package com.irfanirawansukirman.composecrypto.presentation.coindetail

import com.irfanirawansukirman.composecrypto.domain.model.CoinDetail
import com.irfanirawansukirman.composecrypto.domain.model.Coins

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: String = ""
)
