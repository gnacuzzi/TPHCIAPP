package com.example.peraapp.data.model

import com.example.peraapp.data.network.model.NetworkVerifyCode
import java.text.SimpleDateFormat
import java.util.Locale

class VerifyCode(
    var code: String
) {
    fun asNetworkMode() : NetworkVerifyCode {
        return NetworkVerifyCode(
            code = code
        )
    }
}