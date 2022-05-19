package com.irfanirawansukirman.composecrypto.util

import com.irfanirawansukirman.composecrypto.domain.model.Coins

object DataFactory {

    val coins = mutableListOf<Coins>().apply {
        add(Coins("", false, "", 0, ""))
    }

    val emptyCoins = emptyList<Coins>()

    const val error = "There's something went wrong"
}