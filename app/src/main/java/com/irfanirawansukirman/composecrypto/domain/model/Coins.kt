package com.irfanirawansukirman.composecrypto.domain.model

import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinsDto

data class Coins(
    val id: String?,
    val isActive: Boolean?,
    val name: String?,
    val rank: Int?,
    val symbol: String?
)

fun CoinsDto.toCoins(): Coins {
    return Coins(id = id, isActive = isActive, name = name, rank = rank, symbol = symbol)
}
