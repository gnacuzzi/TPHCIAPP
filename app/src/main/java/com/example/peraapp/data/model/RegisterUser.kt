package com.example.peraapp.data.model

import com.example.peraapp.data.network.model.NetworkRegisterUser
import com.example.peraapp.data.network.model.NetworkUser
import java.text.SimpleDateFormat
import java.util.Locale

class RegisterUser (
    var firstName: String,
    var lastName: String,
    var birthDate: String = "2000-04-10",
    var email: String,
    var password: String
){
    fun asNetworkModel(): NetworkRegisterUser {
        return NetworkRegisterUser(
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            email = email,
            password = password
        )
    }
}