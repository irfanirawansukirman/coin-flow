package com.irfanirawansukirman.composecrypto.data.repository

import com.irfanirawansukirman.composecrypto.common.IOTaskResult
import com.irfanirawansukirman.composecrypto.common.performSafeNetworkApiCall
import com.irfanirawansukirman.composecrypto.data.remote.CoinPaprikaApi
import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinDetailDto
import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinsDto
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CoinRepositoryImpl @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi
) : CoinRepository {

//    override suspend fun getCoins(): Flow<IOTaskResult<List<CoinsDto>>> {
//        return performSafeNetworkApiCall { coinPaprikaApi.getCoins() }
//    }

    override suspend fun getCoins(): List<CoinsDto> {
        return coinPaprikaApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinPaprikaApi.getCoinById(coinId)
    }
}