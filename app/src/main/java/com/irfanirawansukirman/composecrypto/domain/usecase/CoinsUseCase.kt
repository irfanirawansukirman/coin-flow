package com.irfanirawansukirman.composecrypto.domain.usecase

import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.model.Coins
import com.irfanirawansukirman.composecrypto.domain.model.toCoins
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() = flow {
        coinRepository.getCoins()
            .onStart { emit(Resource.Loading(true)) }
            .catch { emit(Resource.Error(it.localizedMessage ?: "There's something went wrong")) }
            .onEach { emit(Resource.Success(it.map { it.toCoins() })) }
            .flowOn(Dispatchers.IO)
    }
}

