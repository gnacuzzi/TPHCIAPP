package com.example.peraapp

import com.example.peraapp.data.model.Card
import com.example.peraapp.data.model.Error
import com.example.peraapp.data.model.Payment
import com.example.peraapp.data.model.User
import com.example.peraapp.data.model.WalletDetail

data class HomeUiState(
    val isAuthenticated: Boolean = false,
    val isVerified: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val walletDetail: WalletDetail? = null,
    val cards: List<Card>? = null,
    val currentCard: Card? = null,
    val payments: List<Payment>? = null,
    val error: Error? = null
)

val HomeUiState.canGetCurrentUser: Boolean get() = isAuthenticated
val HomeUiState.canGetAllCards: Boolean get() = isAuthenticated
val HomeUiState.canGetPayments: Boolean get() = isAuthenticated
val HomeUiState.canAddCard: Boolean get() = isAuthenticated
val HomeUiState.canDeleteCard: Boolean get() = isAuthenticated && currentCard != null

