package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.CardPayment
import kotlinx.serialization.Serializable

@Serializable
class NetworkCardPayment (
    var amount: Int,
    var description: String,
    var type: String,
    var cardId: Int,
    var receiverEmail: String
){

}