package com.irfanirawansukirman.composecrypto.domain.repository

import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinDetailDto
import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinsDto
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins(): Flow<List<CoinsDto>>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}