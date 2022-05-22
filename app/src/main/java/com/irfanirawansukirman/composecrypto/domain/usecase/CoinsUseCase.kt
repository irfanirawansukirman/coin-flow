package com.irfanirawansukirman.composecrypto.domain.usecase

import com.irfanirawansukirman.composecrypto.common.CoroutineContextProvider
import com.irfanirawansukirman.composecrypto.common.executeFlowProcess
import com.irfanirawansukirman.composecrypto.domain.model.toCoins
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository,
    private val coroutineContextProvider: CoroutineContextProvider
) {

    suspend operator fun invoke() = executeFlowProcess(coroutineContextProvider) {
        coinRepository.getCoins().map { it.toCoins() }
    }
}

