package com.example.peraapp.data.repository

import com.example.peraapp.data.model.Card
import com.example.peraapp.data.model.WalletDetail
import com.example.peraapp.data.network.WalletRemoteDataSource
import com.example.peraapp.data.network.model.NetworkCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class WalletRepository(
    private val remoteDataSource: WalletRemoteDataSource
) {
    // Mutex to make writes to cached values thread-safe.
    private val cardsMutex = Mutex()
    // Cache of the latest sports got from the network.
    private var cards: List<Card> = emptyList()


    val walletDetailStream: Flow<WalletDetail> =
        remoteDataSource.walletDetailStream
            .map { it.asModel() }

    val getCardsStream: Flow<List<Card>> =
        remoteDataSource.getCardsStream
            .map { list ->
                list.map { it.asModel() } }


    suspend fun addCard(card: Card) : Card {
        val newCard = remoteDataSource.addCard(card.asNetworkModel()).asModel()
        cardsMutex.withLock {
            this.cards = emptyList()
        }
        return newCard
    }

    suspend fun deleteCard(cardId: Int) {
        remoteDataSource.deleteCard(cardId)
        cardsMutex.withLock {
            this.cards = emptyList()
        }
    }
}