package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.Card
import com.example.peraapp.data.model.CardType
import com.example.peraapp.data.model.PaymentCard
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkPaymentCard(
    var id: Int?,
    var number: String,
    var expirationDate: String,
    var fullName: String,
    var type: String,
) {
    fun asModel(): PaymentCard {
        return PaymentCard(
            id = id,
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            type = when (type) { "DEBIT" -> CardType.DEBIT else -> CardType.CREDIT },
        )
    }
}