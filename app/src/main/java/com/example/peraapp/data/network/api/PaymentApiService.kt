package com.example.peraapp.data.network.api

import com.example.peraapp.data.network.model.NetworkBalancePayment
import com.example.peraapp.data.network.model.NetworkCardPayment
import com.example.peraapp.data.network.model.NetworkPayment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PaymentApiService {

    @GET("payment")
    suspend fun getPayments(): Response<List<NetworkPayment>> //necesita parametros

    @POST("payment")
    suspend fun makeBalancePayment(@Body payment: NetworkBalancePayment) : Response<Unit>

    @POST("payment")
    suspend fun makeCardPayment(@Body payment: NetworkCardPayment) : Response<Unit>


}