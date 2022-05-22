package com.irfanirawansukirman.composecrypto.util

import com.irfanirawansukirman.composecrypto.data.remote.dto.CoinsDto
import com.irfanirawansukirman.composecrypto.domain.model.Coins

object DataFactory {

    val coinsDto = mutableListOf<CoinsDto>().apply {
        add(
            CoinsDto(
                id = "",
                isActive = false,
                isNew = false,
                name = "",
                rank = 0,
                symbol = "",
                type = ""
            )
        )
    }

    val coins = mutableListOf<Coins>().apply {
        add(Coins("", false, "", 0, ""))
    }

    val emptyCoins = emptyList<Coins>()

    const val error = "There's something went wrong"
}