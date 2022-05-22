package com.irfanirawansukirman.composecrypto.domain.repository

import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinDetailDto
import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinsDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinsDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}