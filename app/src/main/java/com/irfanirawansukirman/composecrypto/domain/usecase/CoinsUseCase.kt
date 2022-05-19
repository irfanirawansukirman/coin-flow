package com.irfanirawansukirman.composecrypto.domain.usecase

import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.model.Coins
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() = flow<Resource<List<Coins>>> {
        coinRepository.getCoins()
            .asFlow()
            .onStart { emit(Resource.Loading(true)) }
            .catch { emit(Resource.Error(it.localizedMessage ?: "There's something went wrong")) }
            .flowOn(Dispatchers.IO)
    }
}

