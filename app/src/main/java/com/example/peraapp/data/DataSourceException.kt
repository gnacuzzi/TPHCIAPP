package com.example.peraapp.data

class DataSourceException(
    var code: Int,
    message: String,
) : Exception(message)