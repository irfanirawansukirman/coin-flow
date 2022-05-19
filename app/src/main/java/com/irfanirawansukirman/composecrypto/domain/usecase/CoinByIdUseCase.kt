package com.irfanirawansukirman.composecrypto.domain.usecase

import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.model.CoinDetail
import com.irfanirawansukirman.composecrypto.domain.model.Coins
import com.irfanirawansukirman.composecrypto.domain.model.toCoinDetail
import com.irfanirawansukirman.composecrypto.domain.model.toCoins
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow<Resource<CoinDetail>> {
        val response = coinRepository.getCoinById(coinId).toCoinDetail()
        emit(Resource.Success(response))
    }
        .onStart { emit(Resource.Loading(true)) }
        .catch { emit(Resource.Error(it.localizedMessage ?: "There's something went wrong")) }
        .flowOn(Dispatchers.IO)
}