package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.CardPayment

class NetworkCardPayment (
    var amount: Int,
    var description: String,
    var type: String = "CARD",
    var cardId: Int,
    var receiverEmail: String
){

}