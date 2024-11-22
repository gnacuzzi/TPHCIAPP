package com.example.peraapp.data.repository

import com.example.peraapp.data.model.BalancePayment
import com.example.peraapp.data.model.CardPayment
import com.example.peraapp.data.model.Payment
import com.example.peraapp.data.network.PaymentRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PaymentRepository (
    private val remoteDataSource: PaymentRemoteDataSource
){
    suspend fun getPayments(): List<Payment> {
        val result = remoteDataSource.getPayments()
        return result.asModel()
    }

    suspend fun makeBalancePayment(balancePayment: BalancePayment) {
        remoteDataSource.makeBalancePayment(balancePayment.asNetWorkModel())
    }

    suspend fun makeCardPayment(cardPayment: CardPayment) {
        remoteDataSource.makeCardPayment(cardPayment.asNetWorkModel())
    }
}