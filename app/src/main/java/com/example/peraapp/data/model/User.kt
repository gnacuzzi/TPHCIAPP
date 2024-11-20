package com.example.peraapp.data.model

import com.example.peraapp.data.network.model.NetworkUser
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class User(
    var id: Int?,
    var firstName: String,
    var lastName: String,
    var email: String,
    var birthDate: Date?
){
    fun asNetworkModel(): NetworkUser{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return NetworkUser(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            birthDate = birthDate?.let { dateFormat.format(birthDate!!) }
        )
    }
}

