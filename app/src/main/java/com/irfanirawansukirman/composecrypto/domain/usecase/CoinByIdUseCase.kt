package com.irfanirawansukirman.composecrypto.domain.usecase

import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.model.CoinDetail
import com.irfanirawansukirman.composecrypto.domain.model.toCoinDetail
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class CoinByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(coinId: String) = flow<Resource<CoinDetail>> {
        emit(Resource.Success(coinRepository.getCoinById(coinId).toCoinDetail()))
    }
        .onStart { emit(Resource.Loading(true)) }
        .catch { emit(Resource.Error(it.localizedMessage ?: "There's something went wrong")) }
        .flowOn(Dispatchers.IO)
}