package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.RegisterUser
import kotlinx.serialization.Serializable

@Serializable
class NetworkRegisterUser(
    var firstName: String,
    var lastName: String,
    var birthDate: String = "2000-04-10",
    var email: String,
    var password: String
) {
    fun asModel(): RegisterUser {
        return RegisterUser(
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            email = email,
            password = password
        )
    }
}