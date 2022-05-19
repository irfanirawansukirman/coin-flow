package com.irfanirawansukirman.composecrypto.domain.model

import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinDetailDto
import com.irfanirawansukirman.composecrypto.data.remote.dto.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id ?: "",
        name = name ?: "",
        description = description ?: "",
        symbol = symbol ?: "",
        rank = rank ?: 0,
        isActive = isActive ?: false,
        team = teamMember ?: emptyList(),
        tags = tags?.map { it.name ?: "" } ?: listOf("")
    )
}
