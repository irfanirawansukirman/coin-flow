package com.irfanirawansukirman.composecrypto.data.repository

import com.irfanirawansukirman.composecrypto.data.remote.CoinPaprikaApi
import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinDetailDto
import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinsDto
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinsDto> = coinPaprikaApi.getCoins()

    override suspend fun getCoinById(coinId: String): CoinDetailDto =
        coinPaprikaApi.getCoinById(coinId)
}