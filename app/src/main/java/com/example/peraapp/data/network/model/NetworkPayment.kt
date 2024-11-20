package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.Payment
import java.text.SimpleDateFormat
import java.util.Locale

class NetworkPayment(
    var id: Int?,
    var type: String,
    var amount: Int?,
    var balanceBefore: Int?,
    var balanceAfter: Int?,
    var pending: String,
    var linkUuid: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var card: NetworkCard?,
) {
    fun asModel() : Payment {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return Payment(
            id = id,
            type = type,
            amount = amount,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt?.let { dateFormat.format(createdAt!!) },
            updatedAt = updatedAt?.let { dateFormat.format(updatedAt!!) },
            card = card!!.asModel()
        )
    }
}