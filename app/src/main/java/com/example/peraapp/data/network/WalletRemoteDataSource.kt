package com.example.peraapp.data.network

import com.example.peraapp.data.network.api.WalletApiService
import com.example.peraapp.data.network.model.NetworkCard
import com.example.peraapp.data.network.model.NetworkWalletDetail
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WalletRemoteDataSource(
    private val walletApiService: WalletApiService
) : RemoteDataSource() {

    val walletDetailStream: Flow<NetworkWalletDetail> = flow {
        while (true) {
            val walletDetail = handleApiResponse {
                walletApiService.getDetails()
            }
            emit(walletDetail)
            delay(DELAY)
        }
    }

    //no se si esto funciona
    val getCardsStream: Flow<List<NetworkCard>> = flow {
        while (true){
            val cards = handleApiResponse {
                walletApiService.getCards()
            }
            emit(cards)
            delay(DELAY)
        }
    }

    suspend fun addCard(card: NetworkCard): NetworkCard {
        return handleApiResponse {
            walletApiService.addCard(card)
        }
    }

    suspend fun deleteCard(cardId: Int) {
        handleApiResponse {
            walletApiService.deleteCard(cardId)
        }
    }

    companion object {
        const val DELAY: Long = 3000
    }
}