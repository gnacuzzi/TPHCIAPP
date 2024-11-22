package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.BalancePayment
import kotlinx.serialization.Serializable

@Serializable
class NetworkBalancePayment(
    var amount: Int,
    var description: String,
    var type: String,
    var receiverEmail: String
) {

}