package com.irfanirawansukirman.composecrypto.presentation

sealed class Screen(val route: String) {
    object CoinsScreen : Screen("coins_screen")
    object CoinDetailScreen : Screen("coin_detail_screen")
}
