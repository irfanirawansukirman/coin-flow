package com.irfanirawansukirman.composecrypto.domain.usecase

import com.irfanirawansukirman.composecrypto.common.Resource
import com.irfanirawansukirman.composecrypto.domain.model.Coins
import com.irfanirawansukirman.composecrypto.domain.model.toCoins
import com.irfanirawansukirman.composecrypto.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

//    suspend operator fun invoke() = flow<Resource<List<Coins>>> {
//        getViewStateFlowForNetworkCall {
//            coinRepository.getCoins()
//        }
//    }

    operator fun invoke() = flow<Resource<List<Coins>>> {
        emit(Resource.Success(coinRepository.getCoins().map { it.toCoins() }))
    }.onStart { emit(Resource.Loading(true)) }
        .catch { emit(Resource.Error(it.localizedMessage ?: "There's something wrong")) }
        .flowOn(Dispatchers.IO)
}

