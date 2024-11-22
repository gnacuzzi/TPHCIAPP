package com.example.peraapp.data.model

import com.example.peraapp.data.network.model.NetworkCard
import com.example.peraapp.data.network.model.NetworkPaymentCard
import java.text.SimpleDateFormat
import java.util.Locale

class PaymentCard(
    var id: Int?,
    var number: String,
    var expirationDate: String,
    var fullName: String,
    var type: CardType,
) {
    fun asNetworkModel(): NetworkPaymentCard {

        return NetworkPaymentCard(
            id = id,
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            type = when (type) { CardType.DEBIT -> "DEBIT" else -> "CREDIT" },
        )
    }
}