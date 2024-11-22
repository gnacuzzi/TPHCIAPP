package com.example.peraapp.data.model

import com.example.peraapp.data.network.model.NetworkCardPayment

class CardPayment(
    var amount: Int,
    var description: String,
    var type: String = "CARD",
    var cardId: Int,
    var receiverEmail: String
){
    fun asNetWorkModel() : NetworkCardPayment {

        return NetworkCardPayment(
            amount = amount,
            description = description,
            type = type,
            cardId = cardId,
            receiverEmail = receiverEmail,
        )
    }
}