package com.example.peraapp.data.network.model

import com.example.peraapp.data.model.VerifyCode
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkVerifyCode (
    var code: String
){
    fun asModel(): VerifyCode {

        return VerifyCode(
            code = code
        )
    }
}