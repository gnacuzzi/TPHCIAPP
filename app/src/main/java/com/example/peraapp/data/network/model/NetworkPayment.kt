package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.Payment
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkPayment(
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
    var card: NetworkPaymentCard?,
    var payer: NetworkUser,
    var receiver: NetworkUser

) {
    fun asModel() : Payment {
        return Payment(
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
            card = card?.asModel(),
            payer = payer.asModel(),
            receiver = receiver.asModel()
        )
    }
}