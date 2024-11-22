package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.Payment
import kotlinx.serialization.Serializable

@Serializable
class NetworkPaymentList(
    var payments: List<NetworkPayment>
) {
    fun asModel(): List<Payment> {
        return payments.map { it.asModel() }
    }
}