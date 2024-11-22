package com.example.peraapp.data.model

import com.example.peraapp.data.network.model.NetworkBalancePayment

class BalancePayment(
    var amount: Int,
    var description: String,
    var type: String = "BALANCE",
    var receiverEmail: String

) {
    fun asNetWorkModel() : NetworkBalancePayment {

        return NetworkBalancePayment(
            amount = amount,
            description = description,
            type = type,
            receiverEmail = receiverEmail,
        )
    }
}