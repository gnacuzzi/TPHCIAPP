package com.example.peraapp.data.network

import com.example.peraapp.data.network.api.PaymentApiService
import com.example.peraapp.data.network.model.NetworkBalancePayment
import com.example.peraapp.data.network.model.NetworkCardPayment
import com.example.peraapp.data.network.model.NetworkPaymentList

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PaymentRemoteDataSource (
    private val paymentApiService: PaymentApiService
) : RemoteDataSource(){

    suspend fun getPayments(): NetworkPaymentList {
        return handleApiResponse {
            paymentApiService.getPayments()
        }
    }

    suspend fun makeBalancePayment(payment: NetworkBalancePayment) {
        handleApiResponse {
            paymentApiService.makeBalancePayment(payment)
        }
    }

    suspend fun makeCardPayment(payment: NetworkCardPayment) {
        handleApiResponse {
            paymentApiService.makeCardPayment(payment)
        }
    }
}