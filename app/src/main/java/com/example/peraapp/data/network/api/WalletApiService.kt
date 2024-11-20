package com.example.peraapp.data.network.api

import com.example.peraapp.data.network.model.NetworkCard
import com.example.peraapp.data.network.model.NetworkWalletDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WalletApiService {

    @GET("wallet/details")
    suspend fun getDetails(): Response<NetworkWalletDetail>

    @GET("wallet/cards")
    suspend fun getCards(): Response<List<NetworkCard>>

    @POST("wallet/cards")
    suspend fun addCard(@Body card: NetworkCard): Response<NetworkCard>

    @DELETE("wallet/cards/{cardId}")
    suspend fun deleteCard(@Path("cardId") cardId: Int): Response<Unit>
}