package com.example.peraapp.data.model

import com.example.peraapp.data.network.model.NetworkPayment
import java.text.SimpleDateFormat
import java.util.Locale

class Payment (
    var id: Int?,
    var type: String,
    var amount: Int?,
    var balanceBefore: Int?,
    var balanceAfter: Int?,
    var receiverBalanceBefore: Int?,
    var receiverBalanceAfter: Int?,
    var pending: Boolean,
    var linkUuid: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var card: PaymentCard?,
    var payer: User,
    var receiver: User
){
    fun asNetworkModel() : NetworkPayment {
        return NetworkPayment(
            id = id,
            type = type,
            amount = amount,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            receiverBalanceBefore = receiverBalanceBefore,
            receiverBalanceAfter = receiverBalanceAfter,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt,
            updatedAt = updatedAt,
            card = card!!.asNetworkModel(),
            payer = payer.asNetworkModel(),
            receiver = receiver.asNetworkModel()
        )
    }
}