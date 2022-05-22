package com.irfanirawansukirman.composecrypto.domain.usecase

import com.irfanirawansukirman.composecrypto.common.CoroutineContextProvider
import com.irfanirawansukirman.composecrypto.common.executeFlowProcess
import com.irfanirawansukirman.composecrypto.domain.model.toCoinDetail
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CoinByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) {

    suspend operator fun invoke(coinId: String) = executeFlowProcess(coroutineContextProvider) {
        coinRepository.getCoinById(coinId).toCoinDetail()
    }
}