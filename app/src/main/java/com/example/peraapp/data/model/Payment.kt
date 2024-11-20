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
    var pending: String,
    var linkUuid: String?,
    var createdAt: String?,
    var updatedAt: String?,
    var card: Card?,
){
    fun asNetworkMode() : NetworkPayment {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return NetworkPayment(
            id = id,
            type = type,
            amount = amount,
            balanceBefore = balanceBefore,
            balanceAfter = balanceAfter,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt?.let { dateFormat.format(createdAt!!) },
            updatedAt = updatedAt?.let { dateFormat.format(updatedAt!!) },
            card = card!!.asNetworkModel()
        )
    }
}