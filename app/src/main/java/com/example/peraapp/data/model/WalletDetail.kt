package com.example.peraapp.data.model

import java.util.Date

class WalletDetail(
    var id: Int,
    var balance: Double,
    var invested: Double,
    var cbu: String,
    var alias: String,
    var createdAt: Date,
    var updatedAt: Date
) {
}