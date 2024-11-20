package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.BalancePayment

class NetworkBalancePayment(
    var amount: Int,
    var description: String,
    var type: String = "BALANCE",
    var receiverEmail: String
) {

}